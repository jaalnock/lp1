#include<iostream>
using namespace std;

//global variables
int n;
int grid[10][10]={0};
int noofsol=0;

bool isSafe(int level,int pos){
    int x,y;
    for(x=0;x<level;x++){
        if(grid[x][pos]) return false;
    }
    //upper left diagonal
    for(x=level-1,y=pos-1;x>=0 && y>=0 ;x--,y--){
        if(grid[x][y]) return false;
    }
    //upper right diagonal
    for(x=level-1,y=pos+1;x>=0 && y<n;x--,y++){
        if(grid[x][y]) return false;
    }
    return true;
}

void nqueen(int level){
    if(level>=n){
        noofsol++;
        cout<<"Solution: "<<noofsol<<endl;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                cout<<(grid[i][j]?"Q":".")<<" ";
            }
            cout<<endl;
        }
        cout<<endl;
        return;
    }
    //trying to place the queen in all positions in the currenty level
    for(int pos=0;pos<n;pos++){
        if(isSafe(level,pos)){
            grid[level][pos]=1;
            nqueen(level+1);//recursion
            grid[level][pos]=0;//backtracking if necessary
        }
    }
}

int main(){
    cout << "Enter the number of queens: ";
    cin >> n;
    nqueen(0);
    return 0;
}