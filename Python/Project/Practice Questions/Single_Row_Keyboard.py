class Solution:
    def calculateTime(self, keyboard: str, word:str) -> int:
        if len(word) == 0:
            return -1
        hash_map = {}
        counter = 0
        for i in range(len(keyboard)):
            hash_map[keyboard[i]] = i
        
        print(hash_map)
        for j in range(len(word)):
            if j == 0:
                counter += abs(hash_map[keyboard[j]] - hash_map[word[j]])
            else:
                counter += abs(hash_map[word[j]] - hash_map[word[j-1]])
        return counter

sol = Solution()
print(sol.calculateTime(keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"))