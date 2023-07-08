'''
Write a function named count_negatives that takes a list of integers 
as a parameter and returns how many numbers in the list are negative. 
For example, if the list is [5, -1, -3, 20, 47, -10, -8, -4, 0, -6, -6], 
you should return 7.
'''

l1 = [5, -1, -3, 20, 47, -10, -8, -4, 0, -6, -6]
def count_negatives(num):
    l2 = list(filter(lambda x: x < 0, num))
    return len(l2)

print(count_negatives(l1))
# filter any numbers > 0 then return len