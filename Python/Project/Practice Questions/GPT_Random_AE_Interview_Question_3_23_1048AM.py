import polars as pl

class Solution:
    def sessionID(self, events:list[dict]):
        df = pl.DataFrame(events)
        df = df.sort(['user_id','event_timestamp'])
        df = df.with_columns(
            pl.col('event_timestamp').str.strptime(pl.Datetime())
        ).with_columns(
            pl.col('event_timestamp').
            shift(1).
            over(pl.col('user_id')).
            alias('prev_timestamp')
        ).with_columns(
            (pl.col('event_timestamp') - pl.col('prev_timestamp')).
            dt.
            total_minutes().
            alias('minutes_since_last_event')
        ).with_columns(
            pl.when((pl.col('minutes_since_last_event') >= 30) | (pl.col('minutes_since_last_event').is_null())).
            then(1).
            otherwise(0).
            alias('session_marker')
        ).with_columns(
            pl.col('session_marker').
            cum_sum().
            over('user_id').
            alias('session_user_marker')
        ).with_columns(
            pl.concat_str(
                pl.col('user_id'),
                pl.lit('-'),
                pl.col('session_user_marker')
            ).
            alias('session_id')
        ).with_columns(
            pl.col('event_timestamp').
            min().
            over('session_id').
            alias('session_start'),
            pl.col('event_timestamp').
            max().
            over('session_id').
            alias('session_end')
        )
        grouped_df = df.group_by(['user_id','session_id','session_start','session_end']
                                 ).len().sort(['user_id','session_id']).rename({'len':'event_count'})
                                 
        daily = df.group_by(pl.col('session_start').
                        dt.
                        truncate('1d').
                        alias('session_day')).agg(pl.col('user_id').n_unique().alias('dau')
                        )
        average_sessions_per_user = df.group_by(pl.col('session_start').
                                                dt.
                                                truncate('1d').
                                                alias('session_day')).agg(pl.col('user_id').n_unique().alias('dau'),
                                                                          pl.col('session_id').n_unique().alias('unique_sessions')).with_columns(
                                                                              (pl.col('unique_sessions') / pl.col('dau')).alias('average_sesisons_per_user')
                                                                          )
        
        return grouped_df, daily, average_sessions_per_user

sol = Solution()
print(sol.sessionID(events = [
    {"user_id": 1, "event_timestamp": "2025-03-01 09:00:00"},
    {"user_id": 1, "event_timestamp": "2025-03-01 09:10:00"},
    {"user_id": 1, "event_timestamp": "2025-03-01 10:00:00"},
    {"user_id": 2, "event_timestamp": "2025-03-01 09:05:00"},
    {"user_id": 2, "event_timestamp": "2025-03-01 11:30:00"},
    {"user_id": 2, "event_timestamp": "2025-03-02 08:00:00"},
]))