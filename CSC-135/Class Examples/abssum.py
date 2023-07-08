from functools import reduce

l1 = [1,2,-3,4,5]

def maxval(num):
    if num == []:
        return 0
    l2 = list(filter(lambda x,y: x > y, num))
    return l2

print(maxval(l1))
    