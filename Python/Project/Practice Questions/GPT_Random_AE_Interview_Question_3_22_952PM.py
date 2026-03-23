import polars as pl
from datetime import datetime 

now = datetime.now()

class Solution:
    def cohortModel(self, events: list[dict]):

        df = pl.DataFrame(events)

        df = df.with_columns(
            pl.col('event_timestamp').str.strptime(pl.Datetime).alias('event_datetime')
        )

        df = df.with_columns(
            (pl.lit(now) - pl.col('event_datetime')).dt.total_days().alias('day_since_event')
        )

        df = df.with_columns(
            pl.col('event_datetime').dt.truncate('1w').alias('event_week')
        )

        df = df.with_columns(
            (pl.col('day_since_event') // 7).
            alias('weeks_since_event')
        )
        sign_ups = df.filter(
            pl.col('event_type') == 'signup'
        ).select(
            ['user_id','event_datetime','event_week']
        )
        
        non_sign_ups = df.filter(
            pl.col('event_type') != 'signup'
        ).select(
            ['user_id','event_datetime','event_week']
        )

        sign_ups = sign_ups.join(non_sign_ups, on='user_id',how = 'inner', suffix='_post')
        
        sign_ups = sign_ups.with_columns(
            (pl.col('event_datetime_post') - pl.col('event_datetime')).dt.total_days().alias('day_since_event')
        )
        sign_ups = sign_ups.with_columns(
            (pl.col('day_since_event') // 7).alias('weeks_since_event')
        ).with_columns(
            pl.lit(1).alias('count')
        )
        cohort = sign_ups.pivot(
            on = 'weeks_since_event',
            index='event_week',
            values = 'count',
            aggregate_function='sum'
        ).sort('event_week')
        return cohort
sol = Solution()
print(sol.cohortModel(events = [
    {"user_id": 1, "event_type": "signup", "event_timestamp": "2025-03-01 09:00:00"},
    {"user_id": 1, "event_type": "purchase", "event_timestamp": "2025-03-03 12:00:00"},
    {"user_id": 2, "event_type": "signup", "event_timestamp": "2025-03-02 14:00:00"},
    {"user_id": 2, "event_type": "purchase", "event_timestamp": "2025-03-08 16:00:00"},
    {"user_id": 3, "event_type": "signup", "event_timestamp": "2025-03-03 08:00:00"},
    {"user_id": 3, "event_type": "login", "event_timestamp": "2025-03-04 10:00:00"},
]))