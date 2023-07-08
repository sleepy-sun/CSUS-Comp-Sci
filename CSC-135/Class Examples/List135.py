class List135:
	# Constructor. Self is a reference to the object
	def __init__(self, data = None, next = None):
		self._data = data
		self._next = next
	
	def empty(self):
		return self._next == None

	def first(self):
		return self._data

	def rest(self):
		return self._next

	def add(self, data):
		return List135(data, self)

	def __str__(self):
		if (self._next == None):
			return "[]"
		else:
			cur = self
			acc = "[" + str(cur._data)
			cur = cur._next
			while cur._next != None:
				acc = acc + "," + str(cur.data)
				cur = cur._next
				return acc + "]"

# Testing the stuff
a = List135()
b = a.add(1)
c = b.add(2)
print(a)
print(b)
print(c)