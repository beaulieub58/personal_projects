class Solution:
    def confusingNumber(self, n: int) -> bool:
        number_map = {"0":"0","1":"1","6":"9","8":"8","9":"6"}
        characters = []
        for i in str(n):
            if i not in number_map:
                return False
            characters.append(number_map[i])
        
        number = "".join(characters)
        return int(number[::-1]) != n
    

    
sol = Solution()
print(sol.confusingNumber(52))