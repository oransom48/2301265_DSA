import matplotlib.pyplot as plt
import numpy as np
import math

count =0

def FindGCD1(arguments):
  input = []
  array = []
  for i in arguments:
     input.append(i)
  global count 
  def prime_factors(n):
    global count 
    lst = []

    while n % 2 == 0:
        lst.append(2)
        n = n / 2
        count+=1

    for i in range(3, int(math.sqrt(n)) + 1, 2):
        while n % i == 0:
            lst.append(i)
            n = n / i
            count+=1

    if n > 2:
        lst.append(int(n))
    return lst

  result =1

  for i in range(len(input)):
    array.append(prime_factors(input[i]))

  min =[]
  max= []
  if ( len(x) >= len(y)):
      min = y
      max= x
  else :
    min= x
    max =y
  for i in min:
    for j in max:
      if i == j :
        result= i
        break
      if j > i: break

  print(count)
  return result
  
FindGCD1( 36,84,120)