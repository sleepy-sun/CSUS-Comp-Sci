# Write a recursive function named stutter_list that accepts a list of integers as a 
# parameter and replaces every value in the list with two occurrences of that value. 
# For example, suppose a list named s stores these values, from bottom => top:
# 
# [13, 27, 1, -4, 0, 9]
# 
# Then the call of stutter_list(s) should change the list to store the following values:
# 
# [13, 13, 27, 27, 1, 1, -4, -4, 0, 0, 9, 9]
# 
# Notice that you must preserve the original order. In the original list the 9 was at the 
# top and would have been popped first. In the new list the two 9s would be the first values 
# popped from the list. If the original list is empty, the result should be empty as well.
# 
# Constraints: Your solution must be recursive. Do not use any loops. Do not use any auxiliary 
# collections or data structures to solve this problem.
l1 = [13, 27, 1, -4, 0, 9]

def stutter_list(num):
    if len(num) == 0:
        return num
    else:
        tmp = num.pop()
        stutter_list(num)
        num.append(tmp)
        num.append(tmp)
    return num

print(stutter_list(l1))