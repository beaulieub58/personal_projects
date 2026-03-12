from collections import defaultdict
class Solution:
    def apiRead(self, data: dict) -> dict:
    
        hash_map = defaultdict(lambda:defaultdict(int))
    
        for orders in data['orders']:
            for items in orders['items']:
                hash_map[orders['date']][items['product']] += (items['price'] * items['qty'])
    
        #return dict(hash_map)
        return {date: dict(items) for date, items in hash_map.items() }
    
    def harderApiRead(self, data: dict) -> dict:
    
        hash_map = defaultdict(lambda:defaultdict(int))
    
        for orders in data['orders']:
            for items in orders['items']:
                hash_map[orders['date']][items['product']] += (items['price'] * items['qty'])
        simple_hash = {}
        for date, product in hash_map.items():
            simple_hash[date] = max(product,key=product.get)
        return dict(simple_hash)

    
    
    
    
sol = Solution()   
print(sol.harderApiRead(data = {
    "orders": [
        {
            "order_id": 1,
            "date": "2024-01-01",
            "items": [
                {"product": "A", "price": 10, "qty": 2},
                {"product": "B", "price": 20, "qty": 1}
            ]
        },
        {
            "order_id": 2,
            "date": "2024-01-01",
            "items": [
                {"product": "A", "price": 10, "qty": 1}
            ]
        },
        {
            "order_id": 3,
            "date": "2024-01-02",
            "items": [
                {"product": "B", "price": 20, "qty": 3}
            ]
        }
    ]
}))