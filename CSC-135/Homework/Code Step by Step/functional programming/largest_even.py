# Write a function named largest_even that takes a list of integers as a parameter 
# and returns the largest even number from a list of integers. An even integer is 
# one that is divisible by 2. For example, if the list is [5, -1, 12, 10, 2, 8], your 
# function should return 12. You may assume that the list contains at least one even integer.
l1 = [5, -1, 12, 10, 2, 8]

def largest_even(num):
    l2 = max(list(filter(lambda x: x%2 == 0, num)))
    return l2

print(largest_even(l1))