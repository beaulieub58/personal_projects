class Solution:
    def hasDuplicate(self, nums: list[int]) -> bool:
        return len(nums) != len(set(nums))


sol = Solution()
print(sol.hasDuplicates([1, 2, 3, 3]))
print(sol.hasDuplicates([1, 2, 3, 4, 5, 6, 7]))