from List135 import List135
from functools import reduce

# Midterm 1, CSC 135 Fall 2022
# Name: Javier Briseno
# Sac State username: javierbriseno

# When you are done, write your name and username above, save, and
# submit this file to
#
#     https://fileinbox.com/csc135/3/javierbriseno
#
# (Note the 3) with xxyxx replaced by your Sac State username.

# Complete each of the following functions. Each problem will be scored
# separately, but the file as a whole must contain no syntax errors.
# That means running this file should not result in a syntax error.
# If a function has a syntax error, replace its body with a dummy return
# or pass statement to eliminate the error.


# Problem 1: Write a function that uses some combination of map, filter,
# reduce and lambda to sum the x values from a list where lo < x < hi
# and return the sum. You may not call any other built-in function to help.
# For example sum_bounds([1,2,3,4,5], 2, 9) should return 12 (3+4+5).

l1 = [1,2,-3,4,5]
l2 = []
def sum_bounds(xs, lo, hi):
    if xs == []:
        return 0
    l2 = reduce(lambda x,y: x+y,(list(filter(lambda x: lo < x < hi,xs))),0)
    return l2

print(1.1,sum_bounds(l2,1,4))


# Problem 2: Write a function that takes a unary boolean function and a list
# as parameters and returns the first list element that makes the function
# True, or None if there are none. For example first_true(lambda x: x%2==0,
# [1,2,3]) should return 2.
def first_true(f, xs):
    for x in xs:
        if f(x):
            return x


print(2.1,first_true(lambda x: x%2==0,[1,3,5]))

# NOTE: The following are client functions of List135. They must not
# access the _data or _next fields. Only the first(), rest(), add(),
# and empty() methods and List135() constructor are allowed.

# Problem 3: Write a function stutter_list that takes a List135 as a
# parameter and returns a new List135 with each element of the original
# duplicated once. For example [1,2,3] would produce [1,1,2,2,3,3].
def stutter_list(xs):
    if xs.empty():
        return xs
    else:
        tmp = stutter_list(xs.rest())
    return tmp.add(xs.first()).add(xs.first())

xs = List135().add(3).add(2).add(1)
ys = stutter_list(xs)
print(3.1,stutter_list(xs))


# Problem 4: The following is the beginning of a tail recursive function
# that returns the maximum element in a non-empty list of numbers.
# Rewrite only the body of _max_nonempty to make it correct and tail-recursive.
def _max_nonempty(xs, acc):
    if xs.empty():
        return acc
    else:
        if xs.first() > acc:
            acc = xs.first()
        return _max_nonempty(xs.rest(),acc)

def max_nonempty(xs):
    return _max_nonempty(xs.rest(), xs.first())


# Midterm testing will ignore the following indented code. You test here.
if __name__ == '__main__':
    print(1, sum_bounds([1,2,3,4,5], 2, 9) == 12)
    print(1, sum_bounds([], 1, 2) == 0)
    print(2, first_true(lambda x: x%2==0,[1,2,3]) == 2)
    xs = List135().add(3).add(2).add(1)
    ys = stutter_list(xs)
    print(3, isinstance(ys, List135))
    print(3, str(ys) == "[1,1,2,2,3,3]")
    print(4, max_nonempty(xs) == 3)
