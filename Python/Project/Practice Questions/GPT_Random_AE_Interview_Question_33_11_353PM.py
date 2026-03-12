from collections import defaultdict

class Solution:
    def customerRevenuePerDay(self, data:dict) -> dict:
        hash_map = defaultdict(lambda:defaultdict(int))

        for order in data['orders']:
            for item in order['items']:
                hash_map[order['date']][order['customer_id']] += (item['price'] * item['qty'])
        simple_hash = {}
        for k, v in hash_map.items():
            simple_hash[k] = sum(v.values())
        return simple_hash

sol = Solution()
print(sol.customerRevenuePerDay(data = {
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