from collections import defaultdict
class Solution:

    def apiRead(self, data: dict) -> dict:
        hash_map = defaultdict(int)
        for events in data['events']:
            hash_map[events['user']] += 1
        return dict(hash_map)
    
    def harderApiRead(self,data: dict) -> dict:
        hash_map = defaultdict(lambda: defaultdict(lambda: defaultdict(int)))
        for events in data['events']:
            hash_map[events['user']][events['type']][events['page']]+= 1
        return {user: dict(event_counts) for user, event_counts in hash_map.items()}



sol = Solution()
print(sol.harderApiRead(data = {
    "events": [
        {"user": "A", "type": "click", "page": "home"},
        {"user": "B", "type": "click", "page": "product"},
        {"user": "A", "type": "purchase", "page": "checkout"},
        {"user": "A", "type": "click", "page": "product"},
        {"user": "B", "type": "click", "page": "home"}
    ]
}))