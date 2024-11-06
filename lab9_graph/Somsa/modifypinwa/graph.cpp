#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

// Function to perform DFS and store all maximum unique cycles
void dfs(
    const string &current,                      // Current node
    const string &start_node,                   // Starting node to form a cycle
    int unique_count,                           // Number of unique nodes visited
    unordered_map<string, vector<string>> &adj, // Adjacency list
    unordered_map<string, bool> &used_edges,    // Edge usage tracker
    unordered_map<string, bool> &visited_nodes, // Node visit tracker
    vector<string> &path,                       // Current path
    int &max_unique,                            // Maximum unique nodes found in a cycle
    vector<vector<string>> &max_cycles          // All cycles achieving max_unique
)
{
    // Iterate through all outgoing edges from the current node
    for (const auto &neighbor : adj[current])
    {
        string edge_key = current + "->" + neighbor; // Unique edge identifier

        if (!used_edges[edge_key])
        {
            // If the neighbor is the start node and the path forms a cycle
            if (neighbor == start_node && unique_count >= 2)
            {
                // Form a cycle by returning to the start node
                path.push_back(neighbor);

                // Update maximum unique nodes and store the cycle
                if (unique_count > max_unique)
                {
                    max_unique = unique_count;
                    max_cycles.clear();
                    max_cycles.emplace_back(path);
                }
                else if (unique_count == max_unique)
                {
                    max_cycles.emplace_back(path);
                }

                path.pop_back(); // Backtrack
                continue;        // Continue to explore other neighbors
            }

            // Use this edge
            used_edges[edge_key] = true;

            bool was_visited = visited_nodes[neighbor];

            if (!was_visited)
            {
                // Visit the node for the first time
                visited_nodes[neighbor] = true;
                path.push_back(neighbor);
                dfs(neighbor, start_node, unique_count + 1, adj, used_edges, visited_nodes, path, max_unique, max_cycles);
                path.pop_back();                 // Backtrack
                visited_nodes[neighbor] = false; // Reset visit status
            }
            else
            {
                // Visit the node again without increasing unique_count
                path.push_back(neighbor);
                dfs(neighbor, start_node, unique_count, adj, used_edges, visited_nodes, path, max_unique, max_cycles);
                path.pop_back(); // Backtrack
            }

            // Unuse this edge
            used_edges[edge_key] = false;
        }
    }
}

int main(int argc, char *argv[])
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    string start_node;
    unordered_map<string, vector<string>> adj;

    if (argc == 3 && string(argv[1]) == "-f")
    {
        ifstream infile(argv[2]);
        if (!infile)
        {
            cerr << "Error opening file: " << argv[2] << "\n";
            return 1;
        }
        infile >> n >> m;
        // Read edges from infile
        for (int i = 0; i < m; i++)
        {
            string u, v;
            infile >> u >> v;
            if (i == 0)
            {
                start_node = u; // Assuming the first edge's starting node as the start node
            }
            adj[u].push_back(v);
        }
    }
    else
    {
        cin >> n >> m;
        // Read edges from standard input
        for (int i = 0; i < m; i++)
        {
            string u, v;
            cin >> u >> v;
            if (i == 0)
            {
                start_node = u; // Assuming the first edge's starting node as the start node
            }
            adj[u].push_back(v);
        }
    }

    // Variables to track used edges and visited nodes
    unordered_map<string, bool> used_edges;
    unordered_map<string, bool> visited_nodes;

    // Initialize maximum unique nodes in a cycle
    int max_unique = 0;
    vector<vector<string>> max_cycles;

    // Iterate over all nodes as start nodes
    for (const auto &pair : adj)
    {
        string start_node = pair.first;
        vector<string> path = {start_node};
        visited_nodes[start_node] = true;

        // Start DFS to find cycles
        dfs(start_node, start_node, 1, adj, used_edges, visited_nodes, path, max_unique, max_cycles);

        visited_nodes[start_node] = false; // Reset for next start node
    }

    // Output the results
    if (max_unique > 1) // At least two unique nodes to form a cycle
    {
        cout << "Maximum unique nodes in a cycle: " << max_unique << "\n";
        cout << "Total cycles found: " << max_cycles.size() << "\n";
        cout << "Cycles:\n";
        int cycle_number = 1;
        for (const auto &cycle : max_cycles)
        {
            cout << "Cycle " << cycle_number++ << ":\n    ";
            for (size_t i = 0; i < cycle.size(); ++i)
            {
                cout << cycle[i];
                if (i != cycle.size() - 1)
                    cout << " -> ";
            }
            cout << "\n";
        }
    }
    else
    {
        cout << "No cycles found.\n";
    }

    return 0;
}
