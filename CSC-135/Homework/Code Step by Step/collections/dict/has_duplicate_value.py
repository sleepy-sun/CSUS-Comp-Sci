# Write a function named has_duplicate_value that accepts a dictionary from strings to strings as a parameter 
# and returns True if any two keys map to the same value. For example, if a dictionary named m stores 
# {'Marty': 'Stepp', 'Stuart': 'Reges', 'Jessica': 'Miller', 'Amanda': 'Camp', 'Meghan': 'Miller', 'Hal': 'Perkins'}, 
# the call of has_duplicate_value(m) would return True because both 'Jessica' and 'Meghan' map to the value 'Miller'. 
# Return False if passed an empty or one-element dictionary. Do not modify the dictionary passed in.

m = {'Marty': 'Stepp', 'Stuart': 'Reges', 'Jessica': 'Miller', 'Amanda': 'Camp', 'Meghan': 'Miller', 'Hal': 'Perkins'}

def has_duplicate_value(m):
   return len(m.values()) != len(set(m.values()))

print(has_duplicate_value(m))

def has_duplicate_value(dic):
   val_list = dic.values()
   val_set = set(val_list)