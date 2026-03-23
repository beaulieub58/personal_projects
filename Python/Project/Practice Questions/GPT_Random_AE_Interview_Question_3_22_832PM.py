import polars as pl

class Solution:
    def sessionID(self, events: list[dict]):
        df = pl.DataFrame(events)
        df = df.with_columns(
            pl.col("timestamp").str.strptime(pl.Datetime)
        )
        sorted_df = df.sort(['user_id','timestamp']).with_columns(
            pl.col('timestamp').shift(1).over('user_id').alias('prev_timestamp')
        )
        sorted_df = sorted_df.with_columns(
            (pl.col('timestamp') - pl.col('prev_timestamp'))
            .dt.total_minutes()
            .alias('minutes_since_last_view')
        )
        sorted_df = sorted_df.with_columns(
            pl.when((pl.col('minutes_since_last_view') > 30) | (pl.col('minutes_since_last_view').is_null()))
                    .then(1)
                    .otherwise(0)
                    .alias('session_builder')
                    )
        sorted_df = sorted_df.with_columns(
            pl.col('session_builder').
            cum_sum().
            over('user_id').
            alias('session_id_marker')
        )
        sorted_df = sorted_df.with_columns(
            pl.concat_str([pl.col('session_id_marker'),
                          pl.col('user_id')]
                              ,separator="_").
                          alias('session_id'),
        )
        return sorted_df
        
sol = Solution()
print(sol.sessionID(events = [
    {"user_id": 1, "timestamp": "2024-01-01T09:00:00", "event_type": "page_view"},
    {"user_id": 1, "timestamp": "2024-01-01T09:10:00", "event_type": "click"},
    {"user_id": 1, "timestamp": "2024-01-01T09:45:00", "event_type": "page_view"},
    {"user_id": 2, "timestamp": "2024-01-01T10:00:00", "event_type": "page_view"},
    {"user_id": 2, "timestamp": "2024-01-01T10:20:00", "event_type": "click"},
    {"user_id": 1, "timestamp": "2024-01-01T10:50:00", "event_type": "click"},
    {"user_id": 2, "timestamp": "2024-01-01T11:00:00", "event_type": "page_view"},
]))