# a filter will keep elements that make f TRUE.
# will eliminate any value that is false
# a filter returns the things you want to KEEP

lst = [1,2,3,4,5,6,7,8,9,10]

def myfilter(f,lst):
    rval = []
    for x in lst:
        if f(x):
            rval.append(x)
    return rval

lst2 = myfilter(lambda x: x%2 == 0, lst)

print(lst2)