# importing the required module
import matplotlib.pyplot as plt
import math
count =0
digit_record= []
count_record= []
testcase_file = "lab1_Algorithm analysis/testcase/Extra Case2 plot.txt"
def prime_factors(n):
    global count
    lst = []

    while n % 2 == 0:
        lst.append(2)
        n = n / 2
        count += 1

    for i in range(3, int(math.sqrt(n)) + 1, 2):
        while n % i == 0:
            lst.append(i)
            n = n / i
            count += 1

    if n > 2:
        lst.append(int(n))
    return lst
def FindGCD1(*arguments):

    input = []
    array = []
    for i in arguments:
        input.append(int(i))
    global count

    for i in range(len(input)):
        array.append(prime_factors(input[i]))
    result = array[0]
    for i in range(1, len(array)):
        current_factors = array[i]
        common_factors = []
        temp_result = result[:]

        for factor in current_factors:
            if factor in temp_result:
                common_factors.append(factor)
                temp_result.remove(factor)
        result = common_factors
    ans = 1
    for i in result:
        ans *= i
    print("Count:", count)
    print("GCD:", ans)
with open(testcase_file, "r") as f:
    for i in f:
        num = []
        digit_num = []
        count= 0
        str_arr =i.strip().split(",")
        for i in str_arr:
            num.append(int(i))
            digit_num.append(len(str(i)))
        #check group of number in line
        # print(num)
        digit_record.append(sum(digit_num)- len(digit_num))
        FindGCD1(*num[:len(num)+1])
        count_record.append(count)


plt.plot(digit_record,count_record)
plt.xlabel('Digits')
plt.ylabel('Count')
plt.grid(True)
plt.savefig("lab1_Algorithm analysis/graph/gcd1_ExtraCase2.png")