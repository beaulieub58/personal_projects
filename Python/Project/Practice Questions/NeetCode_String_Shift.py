class Solution:
    def stringShift(self, s: str, shift: list[list[int]]) -> str:
        string_list = []
        for i in range(len(s)):
            string_list.append(s[i])
        
        for i in range(len(shift)):
            if shift[i][0] == 0 :
                for j in range(shift[i][1]):
                    string_list.append(string_list[0])
                    string_list.pop(0)

            else:
                for h in range(shift[i][1]):
                    string_list.insert(0, string_list[-1])
                    string_list.pop()

        return str(''.join(string_list))


sol = Solution()
print(sol.stringShift(s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]))