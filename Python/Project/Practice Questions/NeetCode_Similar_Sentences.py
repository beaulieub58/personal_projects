from collections import defaultdict

class Solution:
    def areSentencesSimilar(self, sentence1: list[str], sentence2: list[str],similarPairs: list[list[str]]) -> bool:
        if len(sentence1) != len(sentence2):
            return False
        
        similarWordsMap = defaultdict(set)

        for i, j in similarPairs:
            similarWordsMap[i].add(j)
            similarWordsMap[j].add(i)
        
        for h in range(len(sentence1)):
            if sentence1[h] == sentence2[h] or sentence2[h] in similarWordsMap[sentence1[h]]:
                continue
            return False
        
        return True

sol = Solution()
print(sol.areSentencesSimilar(["great","acting","skills"],["fine","drama","talent"],[["great","fine"],["drama","acting"],["skills","talent"]]))

        