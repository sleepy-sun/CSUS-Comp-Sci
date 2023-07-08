# This file goes over briefly calling another function as a parameter
# and also what a lambda is and how it works exactly.

# do f TO lst
def mymap(f,lst):
    rval = []
    for x in lst:
        rval.append(f(x))
    return rval


def add1(x):
    return x + 1

lst = [1,2,3]
#lst2 = mymap(add1,lst)

#print (lst2)

lambda x: x + 1
lst2 = mymap(lambda x: x+1,lst)

print(lst2)

