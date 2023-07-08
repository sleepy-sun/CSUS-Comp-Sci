# Write a function named num_in_common that accepts two lists of integers 
# as parameters and returns the count of how many unique integers occur 
# in both lists. For example, if two lists named l1 and l2 contains the 
# values [3, 7, 3, -1, 2, 3, 7, 2, 15, 15] and [-5, 15, 2, -1, 7, 15, 36] 
# respectively, your function should return 4 because the elements 
# -1, 2, 7, and 15 occur in both lists. Use one or more sets as storage 
# to help you solve this problem. Do not modify the lists passed in.
blah = [3, 7, 3, -1, 2, 3, 7, 2, 15, 15]
pain = [-5, 15, 2, -1, 7, 15, 36]

def num_in_commmon(l1,l2):
    return  len(set(l1) & set(l2))

print(num_in_commmon(blah,pain))

