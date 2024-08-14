import matplotlib.pyplot as plt
import numpy as np
import math

count = 0

def SieveOfEratosthenes(n):
    global count
    primeFac = []
    spf = [0 for i in range(n+1)]
    spf[1] = 1
    for i in range(2, n+1):
        spf[i] = i

    for i in range(4, n+1, 2):
        spf[i] = 2

    for i in range(3, int(math.sqrt(n))+1):
        if spf[i] == i:
            for j in range(ii, n+1, i):
                if spf[j] == j:
                    spf[j] = i
                    count+=1

    while n != 1:
        primeFac.append(spf[n])
        n = n // spf[n]
    return primeFac

def FindGCD2(m,n):
  global count
  print(" prime factors of m by sieveof eratosthenes are")
  mPrimeFac = SieveOfEratosthenes(int(m))
  print(mPrimeFac)
  print(" prime factors of n by sieveof eratosthenes are")
  nPrimeFac = SieveOfEratosthenes(int(n))
  print(nPrimeFac)

  commonFac = []
  for i in mPrimeFac:
    for j in nPrimeFac:
      count += 1
      if i == j :
        commonFac.append(i)
        break
      if j > i: break
  print(f"common fac are ",commonFac)

  sum = 1
  for i in commonFac:
    sum= i
  print(f"GCD is ",sum)
  
FindGCD2(20,40)
print(f"count = ",count)