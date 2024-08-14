import matplotlib.pyplot as plt
import numpy as np
import math

testcase_file = "lab1_Algorithm analysis/testcase/Regular_Case1.txt"
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

def findGCD3(m,n):
    global count
    count += 1
    if (n==0):
        return m
    if (m>n):
        return findGCD3(n, m%n)
    if (m<n):
        return findGCD3(m, n%m)

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
            num[i+1] = findGCD3(num[i], num[i+1])

        print(f"GCD is {num[len(num)-1]}")
        print(f"count = {count}\n")
        count_record.append(count)


print(digit_record)
print(count_record)

# plot graph
x = np.array(digit_record)
y = np.array(count_record)

plt.plot(x,y)
plt.xlabel("avg digit")
plt.ylabel("count")
plt.savefig("gcd3.png")

print("finish plotting graph")