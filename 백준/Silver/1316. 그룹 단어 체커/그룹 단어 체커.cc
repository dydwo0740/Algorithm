#include <iostream>
#include<algorithm>
#include<queue>
#include <vector>
#include <map>
#include <set>
#include <string.h>

using namespace std;



int main() {
	
	int N;
	int alpha[26];
	int cnt = 0;
	cin >> N;

	set<char> set;

	for (int i = 0;i < N;i++) {
		string str;
		bool flag = true;
		set.clear();
		memset(alpha, 0, 26);

		cin >> str;

		for (int j = 0;j < str.length();j++) {
			if (set.find(str[j]) == set.end()) {
				set.insert(str[j]);
				alpha[str[j] - 'a'] = set.size();
			}
			else if (alpha[str[j] - 'a'] != set.size()) {
				flag = false;
				break;
			}
		}

		if (flag) {
			cnt++;
		}

		
	}


	cout << cnt << endl;



	

	return 0;
}