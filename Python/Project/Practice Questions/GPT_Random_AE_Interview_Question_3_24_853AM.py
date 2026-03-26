import polars as pl
import math
class Solution:
    def cohortModel(self, events: list[dict]):
        df = pl.DataFrame(events)

        df = df.group_by(
            pl.col('user_id')
        ).agg(
            pl.col('timestamp').
            str.
            strptime(pl.Datetime).
            min().
            alias('sign_up_time')
        ).with_columns(
            pl.col('sign_up_time').
            dt.
            truncate('1w').
            alias('sign_up_week')
        ).sort('user_id')

        logins = pl.DataFrame(events).filter(
            pl.col('event_type') == 'login' 
        ).with_columns(
            pl.col('timestamp').
            str.
            strptime(pl.Datetime).
            alias('login_time')
        )
        
        df = df.join(
           logins, on = 'user_id',how = 'inner'
            ).filter(
                pl.col('login_time') > pl.col('sign_up_time')
            ).select(
                ['user_id','sign_up_time','sign_up_week','login_time']
            ).with_columns(
                (pl.col('login_time') - pl.col('sign_up_time')).dt.total_days().alias('weeks_since_signup')
            ).with_columns(
                (pl.col('weeks_since_signup')/7).floor().alias('floored_week_since_signup').cast(pl.Int64)
            )
                
        pivotted = df.pivot(
           on = 'floored_week_since_signup',
           index = 'sign_up_week',
           values = 'user_id',
           aggregate_function= 'count'
        )

        return df ,pivotted


sol = Solution()
print(sol.cohortModel(events = [
    {"user_id": 1, "event_type": "signup", "timestamp": "2025-03-01 09:00:00"},
    {"user_id": 1, "event_type": "login",  "timestamp": "2025-03-03 10:00:00"},
    {"user_id": 1, "event_type": "login",  "timestamp": "2025-03-10 11:00:00"},

    {"user_id": 2, "event_type": "signup", "timestamp": "2025-03-02 12:00:00"},
    {"user_id": 2, "event_type": "login",  "timestamp": "2025-03-04 13:00:00"},

    {"user_id": 3, "event_type": "signup", "timestamp": "2025-03-08 14:00:00"},

    {"user_id": 4, "event_type": "signup", "timestamp": "2025-03-08 15:00:00"},
    {"user_id": 4, "event_type": "login",  "timestamp": "2025-03-15 16:00:00"},
]))