import polars as pl


def totalRevenuePerDay(data:dict):
    df = pl.DataFrame(data['orders']).explode('items').unnest('items')
    df = df.group_by(['date','product']).agg((pl.col('qty') * pl.col('price')).sum())
    return df

print(totalRevenuePerDay(data = {
    "orders": [
        {
            "order_id": 1,
            "customer_id": "C1",
            "date": "2024-01-01",
            "items": [
                {"product": "Laptop", "price": 1000, "qty": 1},
                {"product": "Mouse", "price": 50, "qty": 2}
            ]
        },
        {
            "order_id": 2,
            "customer_id": "C2",
            "date": "2024-01-01",
            "items": [
                {"product": "Laptop", "price": 1000, "qty": 1}
            ]
        },
        {
            "order_id": 3,
            "customer_id": "C1",
            "date": "2024-01-02",
            "items": [
                {"product": "Keyboard", "price": 80, "qty": 1}
            ]
        }
    ]
}))
    
