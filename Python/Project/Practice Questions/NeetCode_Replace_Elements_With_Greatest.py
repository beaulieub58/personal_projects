class Solution:
    def replaceElements(self, arr: list[int]) -> list[int]:
        for i in range(len(arr)-1):
            
            for j in range(i+1,len(arr)):
                arr[i] = max(arr[i+1:])
        
        arr[-1] = -1
        return arr
                    
                






sol = Solution()
print(sol.replaceElements([2,4,5,3,1,2]))