#include <bits/stdc++.h>
using namespace std;
int main(){
    int n;
    cin>>n;

    double priceperday[n];
    double temp;
    for(int i=0;i<n;i++){
        cin>>temp;
        priceperday[i]=temp;
    }
    //หาคู่อันดับที่ค่าต่างกันมากที่สุด
    double maxdiff[3];
    maxdiff[2]=0;
    for(int i=0;i<n;i++){
        for(int j=i+1;j<n;j++){
            if(priceperday[j]-priceperday[i]>=maxdiff[2]){
                maxdiff[0]=i;
                maxdiff[1]=j;
                maxdiff[2]=priceperday[j]-priceperday[i];
            }
        }
    }

    cout<<int(maxdiff[0])+1<<endl;
    cout<<priceperday[int(maxdiff[0])]<<endl;
    cout<<int(maxdiff[1])+1<<endl;
    cout<<priceperday[int(maxdiff[1])]<<endl;
    cout<<maxdiff[2]<<endl;
    cout<<int(maxdiff[1]-maxdiff[0])<<endl;
    return 0;
}