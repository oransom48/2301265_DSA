#include <iostream>
#include <vector>
#include <string>
using namespace std;

// Function to compute KMP prefix array
vector<int> computePrefix(const string &pattern)
{
    int m = pattern.length();
    vector<int> pi(m, 0);
    int k = 0;

    for (int i = 1; i < m; i++)
    {
        while (k > 0 && pattern[k] != pattern[i])
        {
            k = pi[k - 1];
        }
        if (pattern[k] == pattern[i])
        {
            k++;
        }
        pi[i] = k;
    }
    return pi;
}

// Function to search pattern in a string using KMP
vector<int> KMPSearch(const string &text, const string &pattern)
{
    vector<int> positions;
    vector<int> pi = computePrefix(pattern);
    int n = text.length();
    int m = pattern.length();
    int q = 0;

    for (int i = 0; i < n; i++)
    {
        while (q > 0 && pattern[q] != text[i])
        {
            q = pi[q - 1];
        }
        if (pattern[q] == text[i])
        {
            q++;
        }
        if (q == m)
        {
            positions.push_back(i - m + 1);
            q = pi[q - 1];
        }
    }
    return positions;
}

// Structure to store match positions and direction
struct Match
{
    int row, col;
    string direction;
    Match(int r, int c, string d) : row(r), col(c), direction(d) {}
};

// Main function to find patterns in all directions
vector<Match> findPatterns(const vector<vector<char>> &grid, const string &pattern)
{
    vector<Match> matches;
    int n = grid.size();
    int m = grid[0].size();
    int patLen = pattern.length();

    // Left to Right
    for (int i = 0; i < n; i++)
    {
        string row = "";
        for (int j = 0; j < m; j++)
        {
            row += grid[i][j];
        }
        vector<int> pos = KMPSearch(row, pattern);
        for (int p : pos)
        {
            matches.push_back(Match(i + 1, p + 1, "LR"));
        }
    }

    // Right to Left
    for (int i = 0; i < n; i++)
    {
        string row = "";
        for (int j = m - 1; j >= 0; j--)
        {
            row += grid[i][j];
        }
        vector<int> pos = KMPSearch(row, pattern);
        for (int p : pos)
        {
            matches.push_back(Match(i + 1, m - p, "RL"));
        }
    }

    // Up to Down
    for (int j = 0; j < m; j++)
    {
        string col = "";
        for (int i = 0; i < n; i++)
        {
            col += grid[i][j];
        }
        vector<int> pos = KMPSearch(col, pattern);
        for (int p : pos)
        {
            matches.push_back(Match(p + 1, j + 1, "UB"));
        }
    }

    // Down to Up
    for (int j = 0; j < m; j++)
    {
        string col = "";
        for (int i = n - 1; i >= 0; i--)
        {
            col += grid[i][j];
        }
        vector<int> pos = KMPSearch(col, pattern);
        for (int p : pos)
        {
            matches.push_back(Match(n - p, j + 1, "BU"));
        }
    }

    return matches;
}

int main()
{
    // Read alphabet set
    string alphabet;
    getline(cin, alphabet);

    // Read grid dimensions and pattern length
    int n, m, patLen;
    cin >> n >> m >> patLen;

    // Read grid
    vector<vector<char>> grid(n, vector<char>(m));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> grid[i][j];
        }
    }

    // Read pattern
    string pattern;
    cin >> pattern;

    // Compute prefix array
    vector<int> prefix = computePrefix(pattern);

    // Print prefix array
    cout << prefix[0];
    for (int i = 1; i < prefix.size(); i++)
    {
        cout << " " << prefix[i];
    }
    cout << endl;

    // Find and print matches
    vector<Match> matches = findPatterns(grid, pattern);
    for (const Match &match : matches)
    {
        cout << match.row << " " << match.col << " " << match.direction << endl;
    }

    return 0;
}