class Solution:
    def anagramMappings(self, nums1: list[int], nums2: list[int]) -> list[int]:
        mappings = [0] * len(nums1)
        for i in range(len(nums1)):
            for j in range(len(nums2)):
                if nums1[i] == nums2[j]:
                    mappings[i] = j
                    break
        return mappings
    
sol = Solution()
print(sol.anagramMappings(nums1 = [21,5,74,5,74,21],nums2 = [21,5,74,74,5,21]))