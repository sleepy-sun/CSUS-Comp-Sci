# bfparse.py by Michelle Li (michelledli@csus.edu) for CSC 135 Fall 2022
# Parses a bf program
# Used https://krovetz.net/135/w11/tree.html

# This file contains two support classes followed by parsing code.
# scanner is a simple class that delivers tokens to the parser
# node is a simple class for building trees with any number of children

class scanner:
    # toks[i] must evaluate to the i-th token in the token stream.
    # Assumes toks does not change during parsing
    def __init__(self,toks):
        self._toks = toks
        self._i = 0
    
    # If no more tokens exist or current token isn't s, raise exception.
    # Otherwise pass over the current one and move to the next.
    def match(self,s):
        if (self._i < len(self._toks)) and (self._toks[self._i] == s):
            self._i += 1
        else:
            raise Exception
            
    # If any tokens remain return the current one. If no more, return None.
    def next(self):
        if self._i < len(self._toks):
            return self._toks[self._i]
        else:
            return None

# A tree node is a piece of data along with an optional list of children.
# If the _children field is None it indicates the node is a leaf
class node:
    def __init__(self, data):
        self.data = data
        self.children = None
    
    def add_child(self, child):
        if self.children == None:
            self.children = []
        self.children.append(child)
        
    def is_leaf(self):
        return self.children == None

# parse accepts an indexible squence of strings and succeeds without exception
# if the token sequence is entirely consumed and is in L(S). In the case of Bf
# the input will be a string of characters from <>+-.,[]
def parse(input):
    toks = scanner(input)
    root = parseS(toks)
    if toks.next() != None:
        raise Exception
    return root

# --- DO NOT CHANGE ANY CODE ABOVE THIS LINE ---

# Parser for
# S → CS | LS | λ
# L → [ S ]
# C → > | < | + | - | ,  | .

# Consumes sequence of tokens in L(S), returns parse tree for it
def parseS(toks):
    tok = toks.next()
    # Create node to be returned and label it "S"
    rval = node('S')
    if tok in ('>', '<', '+', '-', ',', '.'): #First(C)
        rval.add_child(parseC(toks))
        rval.add_child(parseS(toks))
    elif tok == '[': #First(L)
        rval.add_child(parseL(toks))
        rval.add_child(parseS(toks))
    elif (tok == None) or (tok == ']'):
        rval.add_child(node(''))
    # if invalid input:
    else:
        raise Exception
    return rval

# Consumes sequence of tokens in L(L), returns parse tree for it
def parseL(toks):
    tok = toks.next()
    # Create node to be returned and label it "L"
    rval = node('L')
    if tok == '[': #First(L)
        # Match tok and add first child, a leaf node labeled tok
        toks.match(tok)
        rval.add_child(node(tok))
        # parseS and have a resulting subtree be our second child
        rval.add_child(parseS(toks))
        toks.match(']') #Follow(L)
        rval.add_child(node(']'))
    else:
        raise Exception
    return rval

# Consumes sequence of tokens in L(C), returns parse tree for it
def parseC(toks):
    tok = toks.next()
    # Create node to be returned and label it "C"
    rval = node('C')
    if tok in ('>', '<', '+', '-', ',', '.'): #First
        # Match tok and add first child, a leaf node labeled tok
        toks.match(tok)
        rval.add_child(node(tok))
    else:
        raise Exception
    return rval

