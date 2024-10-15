#include<bits\stdc++.h>
using namespace std;
int main(){
    int n, k, count = 0;
    string str;
    cin >> str;
    cin >> k;
    n = str.length();
    vector<int> grab;
    int p[n] = {0};
    for(int i = 0; i < n; i++){
        if(str[i] == 'G'){
            grab.push_back(i);
        }
    }

    for(int i : grab){
        for(int j = i-k; j <= i+k; j++){
            if(j >= 0 && j < n && str[j] == 'P' && p[j]==0){
                p[j]=1;
                count++;
                break;
            }
        }
    }
    cout << count;
}
