# Write a function named abs_sum that takes a 
# list of integers as a parameter and returns 
# the sum of the absolute values of each 
# element in the list. For example, the absolute
# sum of [-1, 2, -4, 6, -9] is 22. If the list 
# is empty, return 0.

# Use Python's functional programming constructs,
# such as list comprehensions, map, filter, 
# reduce, to implement your function. Do not use 
# any loops or recursion in your solution.
l1 = [1,2,-3,-4,5,6,7,3]

def abs_sum(num):
    return sum(map(abs,num))

print(abs_sum(l1))