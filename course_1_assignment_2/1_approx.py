#!/bin/python

import math
import numpy as np

def f(x):
  return math.sin(x / 5) * math.exp(x / 10) + 5 * math.exp(-x / 2)

mx1 = np.array([[1, 1], [1, 15]])
my1 = np.array([f(1), f(15)])
mw1 = np.linalg.solve(mx1, my1)
def f1(x):
  return mw1[0] + x * mw1[1]

mx2 = np.array([[1, 1, 1], [1, 8, 8**2], [1, 15, 15**2]])
my2 = np.array([f(1), f(8), f(15)])
mw2 = np.linalg.solve(mx2, my2)
def f2(x):
  return mw2[0] + x * mw2[1] + (x**2) * mw2[2]

mx3 = np.array([[1, 1, 1, 1], [1, 4, 4**2, 4**3], [1, 10, 10**2, 10**3], [1, 15, 15**2, 15**3]])
my3 = np.array([f(1), f(4), f(10), f(15)])
mw3 = np.linalg.solve(mx3, my3)
def f3(x):
  return mw3[0] + x * mw3[1] + (x**2) * mw3[2] + (x**3) * mw3[3]

print(f(1))
print(f(4))
print(f(8))
print(f(10))
print(f(15))
print("1:")
print(f1(1))
print(f1(4))
print(f1(8))
print(f1(10))
print(f1(15))
print("2:")
print(f2(1))
print(f2(4))
print(f2(8))
print(f2(10))
print(f2(15))
print("3:")
print(f3(1))
print(f3(4))
print(f3(8))
print(f3(10))
print(f3(15))
