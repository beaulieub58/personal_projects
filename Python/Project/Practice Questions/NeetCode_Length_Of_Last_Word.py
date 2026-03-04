class Solution:
    def lengthOfLastWord(self, s: str) -> int:
        counter = 0
        counter_1 = 0

        if len(s) == 1:
            return 1

        for i in range(len(s)-1,0,-1):
            if s[i] == ' ':
                counter += 1
            else:
                break

        s = s[:len(s)-counter]

        for j in range(len(s)-1,0,-1):
            if s[j] != ' ':
                counter_1 += 1
            else:
                break

        return counter_1

sol = Solution()
print(sol.lengthOfLastWord('   fly me   to   the moon  '))