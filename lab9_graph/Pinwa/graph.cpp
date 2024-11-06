#include <bits/stdc++.h>
using namespace std;

// Add function to perform DFS and fill stack according to finish times
void fillOrder(
    const string &v,
    unordered_map<string, bool> &visited,
    unordered_map<string, vector<string>> &adj,
    stack<string> &Stack)
{
    visited[v] = true;
    cout << "Visiting node: " << v << "\n";
    for (const auto &neighbor : adj[v])
    {
        if (!visited[neighbor])
            fillOrder(neighbor, visited, adj, Stack);
    }
    Stack.push(v);
    cout << "Pushed to stack: " << v << "\n";
}

// Add function to perform DFS on reversed graph and collect SCCs
void DFSUtil(
    const string &v,
    unordered_map<string, bool> &visited,
    unordered_map<string, vector<string>> &rev_adj,
    vector<string> &component)
{
    visited[v] = true;
    component.push_back(v);
    cout << "Processing node: " << v << "\n";
    for (const auto &neighbor : rev_adj[v])
    {
        if (!visited[neighbor])
            DFSUtil(neighbor, visited, rev_adj, component);
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

    // Step 1: Fill the stack with vertices according to their finish times
    cout << "Starting fillOrder DFS\n";
    stack<string> Stack;
    unordered_map<string, bool> visited;
    for (const auto &pair : adj)
    {
        if (!visited[pair.first])
            fillOrder(pair.first, visited, adj, Stack);
    }

    // Optional: Print the stack contents
    cout << "Stack after fillOrder:\n";
    stack<string> tempStack = Stack; // Copy to display contents
    while (!tempStack.empty())
    {
        cout << tempStack.top() << " ";
        tempStack.pop();
    }
    cout << "\n";

    // Step 2: Reverse the graph
    unordered_map<string, vector<string>> rev_adj;
    for (const auto &pair : adj)
    {
        for (const auto &neighbor : pair.second)
        {
            rev_adj[neighbor].push_back(pair.first);
        }
    }

    // Step 3: Process all vertices in order defined by Stack
    cout << "Starting DFS on reversed graph\n";
    visited.clear();
    vector<vector<string>> strongly_connected_components;
    unordered_map<string, int> node_to_component; // Map node to component ID
    int component_id = 1;
    while (!Stack.empty())
    {
        string v = Stack.top();
        Stack.pop();

        if (!visited[v])
        {
            vector<string> component;
            cout << "Starting new component from node: " << v << "\n";
            DFSUtil(v, visited, rev_adj, component);
            cout << "Finished component: ";
            for (const auto &node : component)
            {
                cout << node << " ";
                node_to_component[node] = component_id; // Assign component ID to node
            }
            cout << "\n";
            strongly_connected_components.push_back(component);
            component_id++;
        }
    }

    // Collect edges that connect different components
    vector<tuple<int, int, string, string>> inter_component_edges;
    for (const auto &pair : adj)
    {
        const string &u = pair.first;
        for (const auto &v : pair.second)
        {
            int comp_u = node_to_component[u];
            int comp_v = node_to_component[v];
            if (comp_u != comp_v)
            {
                inter_component_edges.push_back(make_tuple(comp_u, comp_v, u, v));
            }
        }
    }

    // Output the strongly connected components
    cout << "Strongly Connected Components:\n";
    int index = 1;
    for (const auto &component : strongly_connected_components)
    {
        cout << "Component " << index++ << ": ";
        for (const auto &node : component)
            cout << node << " ";
        cout << "\n";
    }

    // Output the edges that connect components
    cout << "Edges connecting components:\n";
    for (const auto &edge : inter_component_edges)
    {
        int comp_u, comp_v;
        string u, v;
        tie(comp_u, comp_v, u, v) = edge;
        cout << "Component " << comp_u << " to " << comp_v << " by " << u << " " << v << "\n";
    }

    // Build the condensation graph
    int num_components = component_id - 1; // Adjust for component_id starting from 1
    vector<unordered_set<int>> condensed_adj(num_components + 1);
    vector<int> in_degree(num_components + 1, 0);
    vector<int> out_degree(num_components + 1, 0);

    // Build condensed adjacency list and calculate in/out degrees
    for (const auto &edge : inter_component_edges)
    {
        int comp_u = get<0>(edge);
        int comp_v = get<1>(edge);
        if (condensed_adj[comp_u].insert(comp_v).second)
        {
            out_degree[comp_u]++;
            in_degree[comp_v]++;
        }
    }

    // Identify source and sink components
    vector<int> sources, sinks;
    for (int i = 1; i <= num_components; ++i)
    {
        if (in_degree[i] == 0)
            sources.push_back(i);
        if (out_degree[i] == 0)
            sinks.push_back(i);
    }

    // Calculate the minimum number of edges to add
    int edges_to_add = max(sources.size(), sinks.size());
    cout << "Minimum edges to add to make the graph strongly connected: " << edges_to_add << "\n";

    // Output the edges to add to make the graph strongly connected
    cout << "Edges to add to make the graph strongly connected:\n";
    for (size_t i = 0; i < edges_to_add; ++i)
    {
        int from_comp = sinks[i % sinks.size()];
        int to_comp = sources[i % sources.size()];

        // Get a node from the sink component
        const string &from_node = strongly_connected_components[from_comp - 1][0];
        // Get a node from the source component
        const string &to_node = strongly_connected_components[to_comp - 1][0];

        cout << "From node " << from_node << " to node " << to_node << "\n";
    }

    return 0;
}