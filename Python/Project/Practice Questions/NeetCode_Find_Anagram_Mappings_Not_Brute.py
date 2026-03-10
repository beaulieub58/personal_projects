class Solution:
    def anagramMappings(self, nums1: list[int], nums2: list[int]) -> list[int]:
        hash_map = {}
        mappings = [0] * len(nums1)
        for i in range(len(nums1)):
            hash_map[nums1[i]] = 0
        for j in range(len(nums2)):
            hash_map[nums2[j]] = j
            
        for h in range(len(mappings)):
            mappings[h] = hash_map[nums1[h]]
        return mappings

        






sol = Solution()
print(sol.anagramMappings(nums1 = [21,5,74,5,74,21],nums2 = [21,5,74,74,5,21]))