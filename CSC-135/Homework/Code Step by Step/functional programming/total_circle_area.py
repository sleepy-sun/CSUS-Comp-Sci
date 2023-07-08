# Write a function named total_circle_area that accepts a list of real numbers 
# representing the radii of the circles as a parameter and returns the sum of 
# the areas of a group of circles, rounded to the nearest integer. Recall that 
# the area of a circle of radius r is π r2. For example, if the list is 
# [3.0, 1.0, 7.2, 5.5], your function should return 289. If the list is empty, 
# return 0.

from cmath import pi
l1 = [1.0, 2.0, 4.0, 8.0, 9.0]

def total_circle_area(num):
    l2 = sum(list(map(lambda x: x*x*pi,num)))
    return round(l2)

print(total_circle_area(l1))

import math
from functools import reduce

def total_circle_area (float_nums):
    if len(float_nums) == 0:
        return 0
    else:
        circ_area = list(map(lambda r: round(r * r * math.pi), float_nums))
        circ_area_sum = reduce (lambda x, y: x + y, circ_area)
        return (circ_area_sum)

def total_circle_area(ls:list)->int:
    return int(round(reduce(lambda acc, x : acc + (x*x*math.pi),ls,0),0))