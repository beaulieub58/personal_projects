import polars as pl
class Solution:
    def jsonRead(self, events: list[dict]):
        df = pl.DataFrame(events)
        df = df.with_columns(
            pl.col('event_timestamp').
            str.
            strptime(pl.Datetime).
            alias('event_datetime')
            )
        df = df.group_by('user_id').agg(
            [
                pl.when(pl.col('event_type') == 'signup').
                then(pl.col('event_datetime')).min().alias('min_sign_up_time'),
                pl.when(pl.col('event_type') == 'purchase').
                then(pl.col('event_datetime')).min().alias('min_purchase_time')
            ]
        ).sort('user_id')
        df = df.with_columns(
            pl.when(pl.col('min_purchase_time') > pl.col('min_sign_up_time')).
            then(pl.col('min_purchase_time')).
            otherwise(None).
            alias('min_p_time'),
            pl.when((pl.col('min_sign_up_time') < pl.col('min_purchase_time')) | (pl.col('min_purchase_time').is_null())).
            then(pl.col('min_sign_up_time')).
            otherwise(None).
            alias('min_s_time')
        ).select(
            ['user_id','min_s_time','min_p_time']
        ).rename({'min_s_time':'signup_time','min_p_time':'purchase_time'}).with_columns(
            pl.when((pl.col('purchase_time') - pl.col('signup_time')).dt.total_hours() < 48).
            then(1).
            otherwise(0).
            alias('Purchase_Within_48_Hours')
        )
        metrics = {
            "purchased_within_48_hours": df.select(pl.col('Purchase_Within_48_Hours').sum()).item(),
            "total_purchases": df.select(pl.col('purchase_time').count()).item()
        }
        purchased_within_48_hours = round(metrics['purchased_within_48_hours']/metrics['total_purchases'],2)
        return df, metrics, purchased_within_48_hours


sol = Solution()
print(sol.jsonRead(events = [
    {"user_id": 1, "event_type": "signup", "event_timestamp": "2025-03-01 09:00:00"},
    {"user_id": 1, "event_type": "purchase", "event_timestamp": "2025-03-02 10:00:00"},

    {"user_id": 2, "event_type": "signup", "event_timestamp": "2025-03-01 11:00:00"},
    {"user_id": 2, "event_type": "purchase", "event_timestamp": "2025-03-05 12:00:00"},

    {"user_id": 3, "event_type": "signup", "event_timestamp": "2025-03-01 12:00:00"},

    {"user_id": 4, "event_type": "signup", "event_timestamp": "2025-03-01 13:00:00"},
    {"user_id": 4, "event_type": "purchase", "event_timestamp": "2025-03-01 14:00:00"},
]))