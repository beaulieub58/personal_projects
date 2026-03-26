import polars as pl

class Solution:
    def listRead(self, events: list[dict]):
        df = pl.DataFrame(events)
        df = df.insert_column(
            4, pl.col('timestamp').str.strptime(pl.Datetime).alias('datetime')
            ).insert_column(
                5, pl.col('datetime').
                dt.
                truncate('1d').
                alias('day')
            ).group_by(['day','product_id']).agg(
                pl.col('amount').
                sum().
                alias('daily_revenue')
                
            ).sort(['day','product_id']).with_columns(
                pl.col('daily_revenue').
                rank('ordinal',descending=True).
                over('day').
                alias('daily_revenue_rank')
            ).sort(['day','daily_revenue_rank'])
        df_filtered = df.filter(
                pl.col('daily_revenue_rank') <= 2
            ).select(['day','product_id','daily_revenue'])
        metrics = {
            'total_revenue': df.select(pl.col('daily_revenue').sum()).item(),
            'total_revenue_high_rank': df_filtered.select(pl.col('daily_revenue').sum()).item()
        }
        revenue_share = metrics['total_revenue_high_rank'] / metrics['total_revenue']
        return df, metrics, revenue_share

sol = Solution()
print(sol.listRead(events = [
    {"user_id": 1, "product_id": "A", "amount": 20, "timestamp": "2025-03-01 09:00:00"},
    {"user_id": 2, "product_id": "A", "amount": 30, "timestamp": "2025-03-01 10:00:00"},
    {"user_id": 3, "product_id": "B", "amount": 50, "timestamp": "2025-03-01 11:00:00"},
    {"user_id": 1, "product_id": "B", "amount": 25, "timestamp": "2025-03-02 09:00:00"},
    {"user_id": 2, "product_id": "A", "amount": 40, "timestamp": "2025-03-02 10:00:00"},
    {"user_id": 3, "product_id": "C", "amount": 60, "timestamp": "2025-03-02 11:00:00"},
]))