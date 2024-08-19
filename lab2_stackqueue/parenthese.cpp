#include <iostream>
#include <stack>
using namespace std;

int main() {
    cin.tie(0)->sync_with_stdio(0);

    // "({()[{}]})"
    string parentheses = "({()[{}]})";
    int parenthesesLength = parentheses.length();

    stack<char> s;
    string message = "";
    bool allpaired = true;

    for (int i=0; i<parenthesesLength; i++) {
        if (parentheses[i] == '(' || parentheses[i] == '{' || parentheses[i] == '[') {
            s.push(parentheses[i]);
        } else {
            if (s.empty()) {
                allpaired = false;
                message = "too much close parentheses";
                break;
            } else if ( (parentheses[i] == ')' && s.top() == '(') || 
                        (parentheses[i] == '}' && s.top() == '{') || 
                        (parentheses[i] == ']' && s.top() == '[') ) {
                s.pop();
            } else {
                allpaired = false;
                message = "wrong parenthese pair";
                break;
            }
        }
    }
    if (!s.empty() && allpaired) {
        allpaired = false;
        message = "too much open parentheses";
    }

    (allpaired) ? cout << "All paired" : cout << message;

    return 0;
}