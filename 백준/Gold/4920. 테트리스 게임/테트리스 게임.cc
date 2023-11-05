#include <iostream>
#include <algorithm>
using namespace std;

int map[110][110];
int tc = 1, n, ans;

int arr[13][4][2] = {
	{{0,0} ,{0,1},{0,2},{0,3}}, // ㅡ
{{0,0},{1,0},{2,0},{3,0}}, // ㅣ
{{0,0},{0,1},{1,1},{1,2}}, // ㄹ
{{0,1},{1,0},{1,1},{2,0}},
{{0,0},{0,1},{0,2},{1,2}}, // ㄱ
{{0,0},{0,1},{1,0},{2,0}},
{{0,0},{1,0},{1,1},{1,2}},
{{0,1},{1,1},{2,1},{2,0}},
{{0,0},{0,1},{1,0},{1,1}}, // ㅁ
{{0,0},{1,0},{1,1},{2,0}}, // ㅏ
{{0,1},{1,0},{1,1},{2,1}}, // ㅓ
{{0,1},{1,0},{1,1},{1,2}}, // ㅗ
{{0,0},{0,1},{0,2},{1,1}} // ㅜ
};

bool inside(int nx, int ny) { return (nx < 0 || ny < 0 || nx >= n || ny >= n); }

void check(int x, int y) {
	bool flag = 1;
	int sum = 0;
	for (int k = 0; k < 13; k++) {
		flag = 1;
		sum = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + arr[k][i][0];
			int ny = y + arr[k][i][1];
			if (inside(nx, ny)) {
				flag = 0;
				break; // 그모양 스킵
			}
			sum += map[nx][ny];
		}

		if (flag) ans = max(sum, ans);
	}
}

int main() {
	while (1) {
		cin >> n;
		if (n == 0) break;
		ans = -987654321;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				check(i, j);
			}
		}

		cout << tc++ << ". " << ans << endl;
	}
}