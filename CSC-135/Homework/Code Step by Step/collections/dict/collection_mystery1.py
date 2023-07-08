collection_mystery = {'cotton': 'shirt', 'tree': 'violin', 'seed': 'tree', 'light': 'tree', 'rain': 'cotton'}	

def collection_mystery1(map):
    result = {}
    for k in map.keys():
        v = map[k]
        if k[0] <= v[0]:
            result[k] = v
        else:
            result[v] = k
    print(result)

collection_mystery1(collection_mystery)