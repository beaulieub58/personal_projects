
class Solution:
    def largestUniqueNumber(self, nums: list[int]) -> int:
        hash_map = {}

        for i in range(len(nums)):
            if nums[i] in hash_map.keys():
                hash_map[nums[i]] += 1
            else:
                hash_map[nums[i]] = 1
        int_list = []
        for i in range(len(nums)):
            if hash_map[nums[i]] == 1 and nums[i] not in int_list:
                int_list.append(nums[i])
            else:
                pass
        if len(int_list) == 0:
            return -1
        else:
            return sorted(int_list)[-1]
sol = Solution()
print(sol.largestUniqueNumber(nums = [5,7,3,9,4,9,8,3,1]))