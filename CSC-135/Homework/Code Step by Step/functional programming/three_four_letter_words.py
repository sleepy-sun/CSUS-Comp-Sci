# Suppose you have a list of strings declared as follows. Write functional code 
# to produce a new list named words2 containing all of the three- or four-letter 
# words in the list.

words = ["four", "score", "and", "seven", "years", "ago","eve","javier","suck", "rat"]

words2 = list(filter(lambda x: len(x) == 4 or len(x) == 3, words))

print(words2)