blah = ['marty', 'stuart', 'helene', 'jessica', 'amanda']
meh = ['sara', 'allison', 'janette', 'zack', 'riley']	
stu = ['zorah', 'alex', 'tyler', 'roy', 'roy', 'charlie', 'phil', 'charlie', 'tyler']	

def mystery(names):
    result = set()
    for element in names:
        if element < names[0]:
            result.add(element)
        else:
            result.clear()
    print(result)

mystery(stu)