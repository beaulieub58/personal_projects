
class Solution:
    def countingElements(self, nums:list[int]):
        
        counter = 0
        for i in range(len(nums)):
            if nums[i] + 1 in nums:
                counter += 1
        return counter

        

sol = Solution()
print(sol.countingElements([1,1,3,3,5,5,7,7]))