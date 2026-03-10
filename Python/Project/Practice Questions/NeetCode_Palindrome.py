from collections import defaultdict
class Solution:
    def isPalindrome(self, s: str) -> bool:
        hash_map = defaultdict(int)
        if s == s[::-1]:
           return True
        for i in range(len(s)):
            hash_map[s[i]] += 1
        total = 0
        for j in hash_map.values():
            if j % 2 != 0:
                total += 1
        return total <= 1
sol = Solution()
print(sol.isPalindrome(s = "carerac"))