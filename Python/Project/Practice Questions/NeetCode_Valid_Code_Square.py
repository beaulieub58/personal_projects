class Solution:
    def validWordSquare(self, words: list[str]) -> bool:
      
      for i in range(len(words)):
         for j in range(len(words[i])):
            if (j >= len(words)) or (i >= len(words[j])) or (words[i][j] != words[j][i]):
               return False

      return True
                   


sol = Solution()
print(sol.validWordSquare(words = 
               ["abcd",
                "bnrt",
                "crm",
                "dt"]))