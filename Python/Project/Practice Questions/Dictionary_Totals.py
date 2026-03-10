from collections import defaultdict
class Solution:
    def countTotals(self, data: dict) -> dict:
        hash_map = defaultdict(int)
        for i in data['orders']:
            hash_map[i['user']] += i['amount']
        return dict(hash_map)

sol = Solution()
print(sol.countTotals(data = {
 "orders": [
   {"user": "A", "amount": 10},
   {"user": "B", "amount": 20},
   {"user": "A", "amount": 5}
 ]
}))