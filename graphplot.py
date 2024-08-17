import matplotlib.pyplot as plt
import numpy as np

count_record = [29,71,87,76,91,543,984,838,560,748,1887,6995,6252,4389,7614,76240,74765,50439,92154,19816,672269,694403,975921,946229,906442,8648877,6061227,1691979,7268361,8356953,81786287,91675656,78851390,68575642,77583795]
digit_record = [2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5,6,6,6,6,6,7,7,7,7,7,8,8,8,8,8]

x = np.array(digit_record)
y = np.array(count_record)

plt.plot(x,y,"-ro",)
plt.xlabel("avg digit")
plt.ylabel("count")
plt.savefig("lab1_Algorithm analysis/graph/gcd2_ExtraCase2.png")

print("finish plotting graph")