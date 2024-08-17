#include <bits/stdc++.h>
#include <chrono>

using namespace std;

vector<long long int> extractNumber(string input)
{
    istringstream iss(input);

    vector<long long int> numbers;

    long long int number;
    char delimiter;

    while (iss >> number)
    {
        numbers.push_back(number);
        iss >> delimiter;
    }

    return numbers;
}

long long int commonPrimeFactor(vector<vector<long long int>> primeFactor)
{
    // find the common prime factor in all the numbers

    vector<long long int> commonPrimeFactors = primeFactor[0];

    for (size_t i = 1; i < primeFactor.size(); ++i)
    {
        vector<long long int> temp;
        set_intersection(commonPrimeFactors.begin(), commonPrimeFactors.end(), primeFactor[i].begin(), primeFactor[i].end(), back_inserter(temp));
        commonPrimeFactors = temp;
    }

    // and return the product of the common prime factors
    long long int result = 1;

    for (size_t i = 0; i < commonPrimeFactors.size(); ++i)
    {
        result *= commonPrimeFactors[i];
    }

    return result;
}

long long int sieveCount = 0;

vector<long long int> generateSPF(long long int n)
{
    vector<long long int> spf(n + 1, 1);

    spf[0] = 0;
    for (long long int i = 2; i <= n; i++)
    {
        if (spf[i] == 1)
        {
            for (long long int j = i; j <= n; j += i)
            {
                if (spf[j] == 1)
                {
                    spf[j] = i;
                    sieveCount++;
                }
            }
        }
    }

    return spf;
}

vector<long long int> primeFactorizeSPF(long long int x, vector<long long int> spf)
{
    vector<long long int> ret;
    while (x != 1)
    {
        ret.push_back(spf[x]);
        x = x / spf[x];
    }
    return ret;
}

long long int gcdUsingSieve(vector<long long int> numbers)
{
    long long int maxElement = *max_element(numbers.begin(), numbers.end());
    vector<long long int> spf = generateSPF(maxElement);

    vector<vector<long long int>> primeFactors;

    for (size_t i = 0; i < numbers.size(); ++i)
    {
        primeFactors.push_back(primeFactorizeSPF(numbers[i], spf));
    }

    return commonPrimeFactor(primeFactors);
}

int main()
{
    string input;
    getline(cin, input);

    vector<long long int> numbers = extractNumber(input);

    auto sieveStart = chrono::high_resolution_clock::now();
    cout << "GCD using Sieve: " << gcdUsingSieve(numbers) << " | count: " << sieveCount << endl;
    auto sieveEnd = chrono::high_resolution_clock::now();
    double sieveTime = chrono::duration_cast<chrono::microseconds>(sieveEnd - sieveStart).count() / 1000.0;
    cout << "Sieve Time: " << fixed << setprecision(3) << sieveTime << "ms" << endl;
    
    return 0;
}