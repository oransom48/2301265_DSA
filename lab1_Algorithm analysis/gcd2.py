import matplotlib.pyplot as plt
import numpy as np
import math

testcase_file = "lab1_Algorithm analysis/testcase/Extra Case2 plot.txt"
digit_record = []
count_record = []
count = 0

def count_digit(n):
    # Base case
    if n == 0:
        return 1

    count = 0
    # Iterate till n has digits remaining
    while n != 0:
        # Remove rightmost digit
        n = n // 10
        # Increment digit count by 1
        count += 1

    return count

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
            for j in range(i, n+1, i):
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

with open(testcase_file, "r") as f:

    # read and calculate GCD for each line
    for i in f:
        num = []
        digit_num = []

        str_arr = i.strip().split(',')

        for i in str_arr:
            num.append(int(i))
            digit_num.append(count_digit(int(i)))
        print(num)

        # set global count to 0
        count = 0

        # record the avarage digit of each line of testcase
        digit_record.append(int(sum(digit_num) / len(digit_num)))

        # find GCD
        for i in range(len(num)-1):
            num[i+1] = FindGCD2(num[i], num[i+1])

        print(f"GCD is {num[len(num)-1]}")
        print(f"count = {count}\n")
        count_record.append(count)

# for debugging
print(digit_record)
print(count_record)

# plot graph
x = np.array(digit_record)
y = np.array(count_record)

plt.plot(x,y,"-ro",)
plt.xlabel("avg digit")
plt.ylabel("count")
plt.savefig("lab1_Algorithm analysis/graph/gcd2_ExtraCase2.png")

print("finish plotting graph")

# FindGCD2(20,40)
# print(f"count = ",count)

#รู้สึกว่า GCD มันไม่ได้เอา commonfac ทุกตัวมาคูณกัน แล้วก็ count มันไม่ได้เป็นของแต่ละอันแต่มันรวมกันมาหมดเลยมั้งนะฆ