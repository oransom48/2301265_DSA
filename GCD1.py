# importing the required module
import numpy as np
import math
count =0
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
     input.append(i)
    global count


    result =[]

    for i in range(len(input)):
        array.append(prime_factors(input[i]))
    for i in range(len(array)):
        current = []
        min  =[]
        max= []
        add = []
        if (i ==0 ): current = array[0]
        else: current = result
        if (len(current) >= len(array[i])):
            max = current
            min= array[i]
        else:
            max =array[i]
            min = current
        for i in min:
            for j in max:
                if (i ==j):
                    add.append(i)
                    break
        result = add
    ans = 1
    for i in result:
        ans *= i
    print(count)
    return ans
print(FindGCD1( 36,84,120,77,59,19))
