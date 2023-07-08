def print_stars1(x):
    if x==0:
        print("")
    else:
        print("*", end="")
        print_stars(x-1)

def print_stars(x):
    while True:
        if x==0:
            print("")
            return
        else:
            print("*", end="")
            x = x-1

print_stars1(6)

def fact(n):
    if n == 0:
        return 1
    else:
        return n * fact(n-1)
        #* is the last operation therefore not tail recursive

def fact(n, acc):
    if n == 0:
        return acc
    else:
        return fact(n-1, n*acc)

'''
This is a way to add multiple
Lines of comment code within 
PYTHON
'''