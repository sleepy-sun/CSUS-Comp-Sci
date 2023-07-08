# we will use a filter to remove the odd numbers and then reduce to sum them up

from functools import reduce

lst = [1,2,3,4]
newlst = [2,4,6,8]
#def sum_evens(lst):
#    acc = 0
#    for x in lst:
#        if x%2 == 0:
#            acc = acc + x
#    return acc

# it is important to set the initial value to 0 to ensure that the list isnt empty 
# or in this case the list could be comprised of JUST odds therefore the code will fail
#def sum_evens(lst):
#    lst2 = filter(lambda x: x%2 == 0, lst)
#    return reduce(lambda acc, x: acc+x, lst2,0)

#print(sum_evens(lst))

#def all_evens(lst):
#    lst2 = filter(lambda x: x%2 != 0, lst) #this will keep all odds
#    return len(lst2) == 0

#this doesnt work for some reason NEEEED to get to the bottom of this
#print(all_evens(lst))

def add_factory(n):
    return lambda x: x+n

#function closer: free variables are captured upon creation ex: the 1 or 2  at the end of the lambda's (which are technically n)
f1 = add_factory(1) #(lambda x: x+1)
f2 = add_factory(2) #(lambda x: x+2)

print(f1(10))
print(f2(10))

