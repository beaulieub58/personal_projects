from collections import defaultdict
class Solution:
    def userActivitySummary(self, events: list[dict]) -> dict:
        hash_map = defaultdict(lambda: defaultdict(int))
        for event in events:
            hash_map[event['user']][event['action']] += 1
        return {user: dict(items) for user, items in hash_map.items()}
    
    def harderUserActivitySummar(self, events: list[dict]) -> dict:
        hash_map = defaultdict(lambda: defaultdict(int))
        for event in events:
            hash_map[event['user']][event['action']] += 1
        simple_hash = {}
        for user, action in hash_map.items():
            simple_hash[user] = max(action, key=action.get)
        return simple_hash
    

sol = Solution()
print(sol.harderUserActivitySummar(events = [
    {"user": "Alice", "action": "click", "timestamp": "2026-03-01T10:00:00"},
    {"user": "Bob", "action": "click", "timestamp": "2026-03-01T10:05:00"},
    {"user": "Alice", "action": "purchase", "timestamp": "2026-03-01T10:10:00"},
    {"user": "Alice", "action": "click", "timestamp": "2026-03-01T10:15:00"},
    {"user": "Bob", "action": "click", "timestamp": "2026-03-01T10:20:00"}
]))