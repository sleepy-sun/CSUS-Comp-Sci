
l1=[1,3,4,6]
l2=[2,5,6]

def merge(xs1, xs2):
   xs3 = xs1 + xs2
   return sorted(xs3)

print(merge(l1,l2))