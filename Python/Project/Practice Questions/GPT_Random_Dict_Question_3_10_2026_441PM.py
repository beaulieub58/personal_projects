from collections import defaultdict
class Solution:
    def apiRead(self, d: dict) -> dict:
        hash_map = defaultdict(int)
        for value in d.values():
            for i in value:
                for j in i['items']:
                    hash_map[i['customer']] += j['price']
                    
        return dict(hash_map)
        

sol = Solution()
print(sol.apiRead(d = {
    "transactions": [
        {"customer": "Alice", "items": [{"name": "apple", "price": 3}, {"name": "banana", "price": 2}]},
        {"customer": "Bob", "items": [{"name": "apple", "price": 3}]},
        {"customer": "Alice", "items": [{"name": "orange", "price": 4}]}
    ]
}))