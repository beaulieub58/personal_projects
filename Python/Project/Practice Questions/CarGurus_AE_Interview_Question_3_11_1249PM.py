from collections import defaultdict
class Solution:
    def modelCars(self, data: list[dict]) -> dict:
        hash_map = defaultdict(lambda: defaultdict(lambda: defaultdict(int)))
        for cars in data:
            hash_map[cars['year']][cars['make']][cars['model']] += 1
        
        #return simple dictionary
        #return {year: dict(model) for year, model in hash_map.items() } 
        simple_hash = {}
        for k, v in hash_map.items():
            simple_hash[k] = sum(sum(models.values()) for models in v.values())
        return simple_hash
    




sol = Solution()
print(sol.modelCars(data = [{'make': 'Nissan', 'model': 'Titan','year': '2020'},
                            {'make': 'Nissan', 'model': 'Titan','year': '2021'},
                            {'make': 'Ford', 'model': 'F-150','year': '2015'},
                            {'make': 'Ford', 'model': 'F-150','year': '2015'},
                            {'make': 'Chevrolet', 'model': 'Silverado','year': '2017'},
                            {'make': 'Ford', 'model': 'F-150','year': '2021'},
                            {'make': 'Honda', 'model': 'Pilot','year': '2010'}]))
