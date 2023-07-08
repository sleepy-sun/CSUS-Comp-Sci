def loop_mystery_exam1(i, j):
    while i != 0 and j != 0:
        i = i // j
        j = (j - 1) // 2
        print(str(i) + " " + str(j) + " ", end="")
    print(str(i))
    return i + j

print(loop_mystery_exam1(1600, 40))