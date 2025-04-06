#include <iostream>
#include<algorithm>
#include<queue>
#include <vector>
#include <map>
#include <set>
#include <string.h>

using namespace std;

vector<int> g[101];
bool visited[101];
int N;

int bfs() {
	queue<int> queue;

	queue.push(1);
	visited[1] = true;

	int cnt = 0;

	while (!queue.empty()) {
		int cur = queue.front();
		queue.pop();

		cnt++;

		for (int i = 0;i < g[cur].size();i++) {
			int next = g[cur][i];

			if (!visited[next]) {
				visited[next] = true;
				queue.push(next);
			}
		}
	}

	return cnt;

}

int main() {
	
	cin >> N;

	int M;

	cin >> M;

	for (int i = 0;i < M;i++) {
		int v1, v2;

		cin >> v1 >> v2;

		g[v1].push_back(v2);
		g[v2].push_back(v1);
	}

	for (int i = 1;i <= N;i++) {
		sort(g[i].begin(), g[i].end());
	}

	int cnt = bfs();
	
	cout << cnt - 1 << endl;

	return 0;
}