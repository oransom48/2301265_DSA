import matplotlib.pyplot as plt
import numpy as np

gcd1_digit_record = [2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5,6,6,6,6,6,7,7,7,7,7,8,8,8,8,8]
gcd1_count_record = [3, 6, 8, 6, 7, 12, 23, 22, 21, 20, 16, 37, 75, 42, 77, 222, 223, 81, 217, 129, 617, 461, 713, 395, 787, 1414, 1034, 746, 1070, 1703, 3087, 7105, 7016, 7494, 5593]

gcd2_digit_record = [2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5,6,6,6,6,6,7,7,7,7,7,8,8,8,8,8]
gcd2_count_record = [29,71,87,76,91,543,984,838,560,748,1887,6995,6252,4389,7614,76240,74765,50439,92154,19816,672269,694403,975921,946229,906442,8648877,6061227,1691979,7268361,8356953,81786287,91675656,78851390,68575642,77583795]

gcd3_digit_record = [2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8]
gcd3_count_record = [2, 5, 4, 4, 5, 7, 9, 5, 6, 9, 9, 9, 10, 10, 10, 13, 12, 9, 10, 10, 19, 11, 14, 14, 12, 14, 12, 9, 12, 16, 15, 15, 19, 18, 18]

x1 = np.array(gcd1_digit_record)
y1 = np.array(gcd1_count_record)
x2 = np.array(gcd2_digit_record)
y2 = np.array(gcd2_count_record)
x3 = np.array(gcd3_digit_record)
y3 = np.array(gcd3_count_record)

plt.plot(x1,y1,"-ro", x2,y2,"-go", x3,y3,"-bo",)
plt.xlabel("avg digit")
plt.ylabel("count")
plt.yscale("log")
plt.legend(["GCD1", "GCD2", "GCD3"], loc="upper left")
plt.savefig("lab1_Algorithm analysis/graph/gcd_all_log.png")

print("finish plotting graph")