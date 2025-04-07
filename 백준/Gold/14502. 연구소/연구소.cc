#include <iostream>
#include<algorithm>
#include<queue>
#include <vector>
#include <map>
#include <set>
#include <string.h>


using namespace std;

int N = 1;
int M = 1;

int row[4] = { 1, -1, 0,0 };
int col[4] = { 0, 0, 1, -1 };

int board[8][8];

int answer = -1;

int bfs() {
	queue<pair<int, int>> queue;
	bool visited[8][8] = { false, };
	int cnt = 0;

	for (int i = 0;i < N;i++) {
		for (int j = 0;j < M;j++) {
			if (board[i][j] == 2) {
				queue.push(make_pair(i, j));
				visited[i][j] = true;
			}
		}
	}

	while (!queue.empty()) {
		int cx = queue.front().first;
		int cy = queue.front().second;

		queue.pop();

		for (int i = 0;i < 4;i++) {
			int nx = cx + row[i];
			int ny = cy + col[i];

			if (0 <= nx && nx < N && 0 <= ny && ny < M) {
				if (!visited[nx][ny] && board[nx][ny] == 0) {
					queue.push(make_pair(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}

	for (int i = 0;i < N;i++) {
		for (int j = 0;j < M;j++) {
			if (!visited[i][j] && board[i][j] == 0) {
				cnt++;
			}
		}
	}

	return cnt;
}

void combination(int output[3], bool visited[64], int start, int depth) {
	if (depth == 3) {
		answer = max(answer, bfs());
		return;
	}

	for (int i = start;i < N * M;i++) {
		//cout << visited[i] << " " << i / N << " " << i % N << endl;
		if (!visited[i] && board[i / M][i % M] == 0) {
			visited[i] = true;
			output[depth] = i;
			//cout << i << endl;
			board[i / M][i % M] = 1; // 벽을 세웁니다.
			combination(output, visited, i + 1, depth + 1);
			visited[i] = false;
			board[i / M][i % M] = 0;
		}
	}
}



int main() {

	cin >> N >> M;

	for (int i = 0;i < N;i++) {
		for (int j = 0;j < M;j++) {
			cin >> board[i][j];
		}
	}

	int output[3];
	bool visited[64] = { false, };

	combination(output, visited, 0, 0);


	cout << answer << endl;

	

	return 0;
}