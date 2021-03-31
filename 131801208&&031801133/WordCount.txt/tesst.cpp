#include<iostream>
#include<map>
#include<fstream> 
using namespace std;
int main(){
	ifstream in("input1.txt", ios::in);
	istreambuf_iterator<char> beg(in), end;
	string input(beg, end);
	cout<<input;
}
