from collections import defaultdict
class Solution:
    def pivotDictionary(self, tup) -> dict:
        hash_map = defaultdict(dict)
        
        for i in tup:
            hash_map[i[0]][i[1]] = i[2]
            
                
        return hash_map


sol = Solution()
print(sol.pivotDictionary(tup = [
 ("2024-01","A",10),
 ("2024-01","B",20),
 ("2024-02","A",15)
]))