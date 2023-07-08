from parse_hw import parse1, parse2

try:
    parse1("aabxbaa")
except:
    print("Reject")
else:
    print("Accept")

try:
    parse2("aabxbaa")
except:
    print("Reject")
else:
    print("Accept")
