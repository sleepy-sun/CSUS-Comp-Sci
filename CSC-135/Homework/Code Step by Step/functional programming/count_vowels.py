# Write a function called count_vowels that counts the number 
# of vowels in a given string. A vowel is an A, E, I, O, or U, 
# case-insensitive. For example, if the string is "SOO beautiful", 
# there are 7 vowels.
words = 'SOO beautiful'

# find if x is IN the parameter you pass through
def count_vowels(string):
    l2 =  list(filter(lambda x: x in 'AaEeIiOoUu' ,string))
    return len(l2)

print(count_vowels(words))