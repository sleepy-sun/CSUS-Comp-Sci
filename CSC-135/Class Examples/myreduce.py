# 

lst = [1,2,3,4]

def myreduce(f,lst, init = None):
    if init == None: #this makes sure the list isnt empty and if it is then it return as an error
        acc = lst[0]
        i = 1
        while i < len(lst):
            acc = f(acc,lst[i])
            i = i + 1
        return acc
    else:
        acc = init
        for x in lst:
            acc = f(acc,x)
        return acc

sum = myreduce(lambda acc,x: acc+x,lst,0)

print(sum)