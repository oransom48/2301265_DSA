count = 0

def primeFactors(n):
    global count

    spf = [0 for i in range(n+1)]
    spf[1] = 1

    for i in range(2, n+1):
        spf[i] = i
    for i in range(4, n+1, 2):
        spf[i] = 2

    for i in range(3, int(n*0.5)+1):
        if spf[i] == i:
            for j in range(ii, n+1, i):
                count += 1
                if spf[j] == j:
                    spf[j] = i

    primeFac = []
    while n != 1:
        primeFac.append(spf[n])
        n = n // spf[n]
    return primeFac

def commonPrimeFac(a1, a2):
    global count

    cpf = []
    for i in range(len(a1)):
        for j in range(len(a2)):
            count += 1
            if a1[i] == a2[j]:
                count += 1
                cpf.append(a1[i])
                break
    return cpf

def findGCD2(m, n):
    global count

    cpf = commonPrimeFac(primeFactors(m), primeFactors(n))
    gcd = 1
    for i in cpf:
        count += 1
        gcd *= i
    return gcd

with open(testCaseFile, "r") as f:
    for i in f:
        m, n = i.strip().split(',')
        m = int(m)
        n = int(n)
        count = 0
        print(f"GCD of {m} and {n} is {findGCD2(m,n)}: ")
        print(f"count = {count}\n")