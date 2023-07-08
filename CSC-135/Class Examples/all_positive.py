'''
Write a function named all_positive that takes a list of numbers as a parameter and 
returns False if any of them are negative or zero, and True otherwise. 
(This means the empty list should return True too.)

You will write the function twice. Once here and once in another Question. 
Both versions should use higher-order functions and no loops or 
list-comprehensions to solve the problem.

The version you write for this question should use map to create a list of 
booleans, and then use reduce to determine the answer. To support length 0 and 
1 lists, use reduce(func, list, init) with three parameters where the third 
parameter is the initial value of the reduction (ie, func(init, list[0]) 
is the first invocatin of func instead of func(list[0], list[1]).
'''
from functools import reduce


l1 = [2,3,4,-5,6]
l3 = [1,2,-230,0]

def is_true(a, b):
   return bool(a or b)

def all_positives(lst):
    if lst == []:
        return True
    else: 
       return reduce(lambda x,y: bool(x and y),list(map(lambda x: x > 0 if True else False, lst)),0)

print(all_positives(l1))


def all_positives2(lst):
    if lst == []:
        return True
    else:
        temp = list(map(lambda x: x > 0 if True else False, lst))
        return reduce(lambda a, b: bool(a and b), temp)

print(all_positives2(l1))
