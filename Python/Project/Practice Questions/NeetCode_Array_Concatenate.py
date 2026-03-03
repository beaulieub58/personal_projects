class Solution:
    def getConcatenation(self, nums: list[int]) -> list[int]:
        for i in range(len(nums)):
            nums.append(nums[i])
        return nums

sol = Solution()
print(sol.getConcatenation([1,4,1,2]))