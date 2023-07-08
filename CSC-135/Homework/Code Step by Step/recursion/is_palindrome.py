# Write a recursive function named is_palindrome that accepts a string parameter and returns 
# True if the string is the same forwards as backwards, ignoring capitalization. For example, 
# the call of is_palindrome("Madam") should return True.
l1 = 54
l2 = "madam"

def _is_palindrome(s, lo, hi):
    if (hi <= lo):
        return True
    else:
        return (s[lo].upper() == s[hi].upper()) and _is_palindrome(s, lo+1, hi-1)

def is_palindrome(s):
    return _is_palindrome(s, 0, len(s)-1)


print(is_palindrome(l2))

