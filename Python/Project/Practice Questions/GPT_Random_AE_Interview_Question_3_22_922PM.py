import polars as pl
from datetime import datetime 

now = datetime.now()

class Solution:
    def findSignUp(self, events: list[dict]):

        df = pl.DataFrame(events)
        df = df.filter(pl.col('event_type') == 'signup')
        df = df.with_columns(
            pl.col('event_date').str.strptime(pl.Datetime)
        )
        df = df.with_columns(
            (pl.lit(now) - pl.col('event_date')).dt.total_days().
            alias('days_since_signup')
        )

        return df



sol = Solution()
print(sol.findSignUp(events = [
    {"user_id": 1, "event_date": "2024-01-01", "event_type": "signup"},
    {"user_id": 1, "event_date": "2024-01-02", "event_type": "login"},
    {"user_id": 1, "event_date": "2024-01-04", "event_type": "login"},
    {"user_id": 2, "event_date": "2024-01-01", "event_type": "signup"},
    {"user_id": 2, "event_date": "2024-01-03", "event_type": "login"},
    {"user_id": 3, "event_date": "2024-01-02", "event_type": "signup"},
    {"user_id": 3, "event_date": "2024-01-03", "event_type": "login"},
]))