#include <bits/stdc++.h>

using namespace std;

pair<vector<int>, vector<int>> do_Bellman_Ford(vector<vector<pair<int, int>>> &adjList, int startNode)
{
    int n = adjList.size();
    vector<int> dist(n, INT_MAX);
    vector<int> parent(n, -1);
    dist[startNode] = 0;

    // Relax edges repeatedly
    for (int i = 1; i < n; ++i)
    {
        for (int u = 0; u < n; ++u)
        {
            for (auto &[v, w] : adjList[u])
            {
                if (dist[u] != INT_MAX && dist[u] + w < dist[v])
                {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                }
            }
        }
    }

    // Optional: Check for negative-weight cycles
    for (int u = 0; u < n; ++u)
    {
        for (auto &[v, w] : adjList[u])
        {
            if (dist[u] != INT_MAX && dist[u] + w < dist[v])
            {
                // Handle negative cycle
                cerr << "Error: Negative weight cycle detected.\n";
                exit(1);
            }
        }
    }
    return {dist, parent};
}

bool assigned_all(vector<int> &population)
{
    for (int i = 1; i < population.size(); ++i)
    {
        if (population[i] != 0)
        {
            return false;
        }
    }

    return true;
}

int main()
{
    int n, m, c;
    cin >> n >> m >> c;

    vector<int> population(n);

    for (int i = 0; i < n; ++i)
    {
        cin >> population[i];
    }

    vector<vector<pair<int, int>>> adjList(n);

    for (int i = 0; i < m; ++i)
    {
        int from, to, weight;
        cin >> from >> to >> weight;
        from--; // Adjust to 0-based index
        to--;   // Adjust to 0-based index
        adjList[from].emplace_back(to, weight);
    }

    int availablePopulation;

    cin >> availablePopulation;

    auto [cost, parent] = do_Bellman_Ford(adjList, 0); // Start from node 0 instead of 1

    vector<int> costOfEachHuman;
    vector<vector<int>> paths; // Store paths for each person

    vector<pair<int, int>> contryCost_IndexOfPopulation;

    for (int i = 0; i < n; ++i)
    {
        contryCost_IndexOfPopulation.push_back({cost[i], i});
    }

    sort(contryCost_IndexOfPopulation.begin(), contryCost_IndexOfPopulation.end());

    // Modify the assignment logic to assign K people with minimum cost
    bool assigned = false;
    while (availablePopulation > 0 && !contryCost_IndexOfPopulation.empty())
    {
        assigned = false;
        for (auto &[costValue, country] : contryCost_IndexOfPopulation)
        {
            if (population[country] > 0)
            {
                costOfEachHuman.push_back(costValue + c);

                // Construct path
                vector<int> path;
                int current = country;
                while (current != -1)
                {
                    path.push_back(current + 1); // Convert back to 1-based indexing
                    current = parent[current];
                }
                reverse(path.begin(), path.end());
                paths.push_back(path);

                population[country]--;
                availablePopulation--;
                assigned = true;
                if (availablePopulation == 0)
                    break;
            }
        }
        if (!assigned)
        {
            break;
        }
    }

    if (availablePopulation > 0)
    {
        for (int i = 0; i < availablePopulation; ++i)
        {
            costOfEachHuman.push_back(-1);
            paths.push_back({}); // Empty path for impossible cases
        }
    }

    // Create pairs of cost and index to maintain original order for paths
    vector<pair<int, int>> costWithIndex;
    for (int i = 0; i < costOfEachHuman.size(); ++i)
    {
        costWithIndex.push_back({costOfEachHuman[i], i});
    }
    sort(costWithIndex.begin(), costWithIndex.end());

    // Separate valid and invalid persons
    vector<pair<int, int>> validPersons;
    vector<pair<int, int>> invalidPersons;
    for (auto &[cost, index] : costWithIndex)
    {
        if (cost != -1)
            validPersons.push_back({cost, index});
        else
            invalidPersons.push_back({cost, index});
    }

    // Output valid persons and their paths
    for (auto &[cost, index] : validPersons)
    {
        cout << "Person #" << index + 1 << ":\n";
        cout << "Total cost: " << cost << endl;
        cout << "Path length: " << paths[index].size() << endl;
        cout << "Route: ";
        for (int node : paths[index])
        {
            cout << node;
            if (node != paths[index].back())
                cout << " -> ";
        }
        cout << endl;
        cout << "-------------------\n";
    }

    // Output invalid persons
    for (auto &[cost, index] : invalidPersons)
    {
        cout << "Person #" << index + 1 << ":\n";
        cout << "Cannot be assigned (Cost: -1)\n";
        cout << "No valid path available\n";
        cout << "-------------------\n";
    }

    return 0;
}

/*14 27 1000
1 2 1 2 2 1 2 2 1 1 1 1 2 1
1 2 20
1 6 200
1 10 400
1 14 12
2 11 13
3 12 2
4 1 17
4 3 12
4 7 15
5 4 9
6 2 20
6 4 2
7 2 1
7 13 5
8 3 16
8 12 3
9 8 14
9 13 19
10 5 9
10 6 10
11 5 15
11 9 14
11 14 16
12 3 18
12 8 6
12 11 19
13 4 16
22*/