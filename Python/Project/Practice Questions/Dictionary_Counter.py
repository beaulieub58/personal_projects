class Solution:
    def dictionaryCounter(self, dicto: list[dict]) -> dict:
        hash_map = {}
        for i in dicto:
            user = i['user']
            orders = i['orders']
            if user not in hash_map:
                hash_map[user] = 0
            hash_map[user] += sum(orders)

        return hash_map

sol = Solution()
print(sol.dictionaryCounter(dicto = [
    {"user": "alice", "orders": [10, 15]},
    {"user": "bob", "orders": [7]},
    {"user": "alice", "orders": [3]},
    {"user": "charlie", "orders": [12, 8, 5]},
    {"user": "david", "orders": [20]},
    {"user": "alice", "orders": [7, 9]},
    {"user": "bob", "orders": [4, 6]},
    {"user": "emily", "orders": [13, 2]},
    {"user": "charlie", "orders": [11]},
    {"user": "frank", "orders": [5, 5, 5]},
    {"user": "emily", "orders": [9]},
    {"user": "david", "orders": [14, 3]},
]))