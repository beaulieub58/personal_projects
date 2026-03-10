class Solution:
    def topProducer(self, sales: dict) -> dict:
       lists = []
       for key, value in sales.items():
           lists.append((key,value))
       lists = sorted(lists, key=lambda x: x[1])
       return lists[-1][0], lists [-2][0]

            
           

sol = Solution()
print(sol.topProducer(sales = {
 "A": 100,
 "B": 200,
 "C": 150
}))