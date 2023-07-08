# Write a function called glue_reverse that accepts a list of strings 
# as its parameter and returns a single string consisting of the list's 
# elements concatenated together in reverse order. For example, if the 
# list stores ["the", "quick", "brown", "fox"], you should return 
# "foxbrownquickthe". If the list is empty, return an empty string, "".
from functools import reduce


l1 = ["the", "quick", "brown", "fox"]

def glue_reverse(word):
    l2 = reduce(lambda x,y: y+x,word,"")
    return l2

print(glue_reverse(l1))