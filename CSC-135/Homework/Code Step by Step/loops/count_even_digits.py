# Write a function named count_even_digits that accepts two integers as parameters 
# and returns the number of even-valued digits in the first number. An even-valued 
# digit is either 0, 2, 4, 6, or 8. The second value represents how many digits the 
# number has. The second value is guaranteed to match the number of digits in the 
# first number.

# For example, the number 8546587 has four even digits (the two 8s, the 4, and the 6), 
# so the call count_even_digits(8346387, 7) should return 4.

# You may assume that the values passed to your function are non-negative.

meh = 12343
ste = 5

def count_even_digits(first,second):
    tick = 0
    list = [int(x) for x in str(first)]
    for num in list:
        if (num % 2) == 0:
            tick += 1
    return tick

print(count_even_digits(meh,ste))