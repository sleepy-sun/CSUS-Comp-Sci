# Write a function named double_list that takes a list as a parameter 
# and returns a list of integers that contains double the elements in 
# the initial list. For example, if the initial list is [2, -1, 4, 16], 
# you should return [4, -2, 8, 32].
l1 = [2, -1, 4, 16]

# need to make a new list to return since I'm just mapping the new info and not
# doing anything with it.
def double_list(num):
    l2 = list(map(lambda x: x*2,num))
    return l2

print(double_list(l1))