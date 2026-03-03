class Solution:
    def isAnagram(self, s: str, t: str) -> bool:

        if len(s) != len(t):
            return False
        
        s_dict = {}
        t_dict = {}

        for i in range(len(s)):
            counter = 0
            if s[i] in s_dict.keys():
                s_dict[s[i]] = s_dict[s[i]] + 1
            else:
                s_dict[s[i]] = counter + 1
            counter = 0
            if t[i] in t_dict.keys():
                t_dict[t[i]] = t_dict[t[i]] + 1
            else:
                t_dict[t[i]] = counter + 1

        outcomes = []

        for j in range(len(s)):
            
            outcomes.append(s_dict.get(s[j],0) == t_dict.get(s[j],0))
            outcomes.append(s_dict.get(t[j],0) == t_dict.get(t[j],0))
        
        return all(outcomes)

sol = Solution()
print(sol.isAnagram(s = 'jar',t = 'jam'))