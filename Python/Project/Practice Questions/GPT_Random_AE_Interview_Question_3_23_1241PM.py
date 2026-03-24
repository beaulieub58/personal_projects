import polars as pl
import math

class Solution:
    def sessionize(self, events: list[dict]):
        df = pl.DataFrame(events)
        df = df.with_columns(pl.col('event_timestamp').
                             str.
                             strptime(pl.Datetime).
                             alias('event_datetime')
                             )
        df = df.group_by(pl.col('user_id')).agg([
            pl.when(pl.col('event_type') == 'visit').
            then(pl.col('event_datetime')).
            min().
            alias('visit_datetime'),
            pl.when(pl.col('event_type') == 'signup').
            then(pl.col('event_datetime')).
            min().
            alias('signup_datetime'),
            pl.when(pl.col('event_type') == 'purchase').
            then(pl.col('event_datetime')).
            min().
            alias('purchase_datetime')
            ]).sort('user_id')
        df = df.with_columns(
            pl.when(pl.col('visit_datetime') >= pl.col('signup_datetime')).
            then(None).
            otherwise(pl.col('visit_datetime')).
            alias('v_datetime'),
            pl.when(pl.col('signup_datetime') >= pl.col('purchase_datetime')).
            then(None).
            otherwise(pl.col('signup_datetime')).
            alias('s_datetime')
        ).select(['user_id','v_datetime','s_datetime','purchase_datetime']).rename(
            {'v_datetime':'visit_datetime','s_datetime':'signup_datetime'},
        )
        metrics = {
           "visit": df.select(pl.col('visit_datetime').is_not_null().sum()).item(),
           "signup": df.select(pl.col('signup_datetime').is_not_null().sum()).item(),
           "purchase": df.select(pl.col('purchase_datetime').is_not_null().sum()).item()
        }
        
        signup_rate = round(metrics['signup'] / metrics['visit'],2)
        purchase_rate = round(metrics['purchase'] / metrics['signup'],2)
        return df, metrics, signup_rate, purchase_rate

sol = Solution()
print(sol.sessionize(events = [
    {"user_id": 1, "event_type": "visit", "event_timestamp": "2025-03-01 09:00:00"},
    {"user_id": 1, "event_type": "signup", "event_timestamp": "2025-03-01 09:05:00"},
    {"user_id": 1, "event_type": "purchase", "event_timestamp": "2025-03-01 09:10:00"},

    {"user_id": 2, "event_type": "visit", "event_timestamp": "2025-03-01 10:00:00"},
    {"user_id": 2, "event_type": "signup", "event_timestamp": "2025-03-02 11:00:00"},

    {"user_id": 3, "event_type": "visit", "event_timestamp": "2025-03-01 12:00:00"},

    {"user_id": 4, "event_type": "visit", "event_timestamp": "2025-03-01 13:00:00"},
    {"user_id": 4, "event_type": "signup", "event_timestamp": "2025-03-01 13:05:00"},
    {"user_id": 4, "event_type": "purchase", "event_timestamp": "2025-03-05 14:00:00"},
] ))