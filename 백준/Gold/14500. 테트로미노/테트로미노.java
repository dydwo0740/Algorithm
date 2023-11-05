
import java.io.*;
import java.util.*;


public class Main {
    static class Location {
        int x;
        int y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};

    static int ans = -Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];

        boolean[][] visited = new boolean[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                int sum = board[i][j];
                dfs(board, visited, i, j, 1, sum);
                visited[i][j] = false;
            }
        }

        // ㅗ 모양의처리

        /*for (int i = 0; i < N; i++) {
            int cnt = 0;
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += board[i][j];
                cnt++;
                if (cnt == 3) {
                    if (i == 0) {
                        sum += board[i + 1][j - 1];
                    } else if (i == N - 1) {
                        sum += board[i - 1][j - 1];
                    } else {
                        sum += Math.max(board[i + 1][j - 1], board[i - 1][j - 1]);
                    }
                    ans = Math.max(sum, ans);
                    sum = 0;
                    cnt = 0;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            int cnt = 0;
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += board[j][i];
                cnt++;
                if (cnt == 3) {
                    if (i == 0) {
                        sum += board[j - 1][i + 1];
                    } else if (i == M - 1) {
                        sum += board[j - 1][i - 1];
                    } else {
                        sum += Math.max(board[j - 1][i + 1], board[j - 1][i - 1]);
                    }
                    ans = Math.max(sum, ans);
                    sum = 0;
                    cnt = 0;
                }
            }
        }*/


        bw.write(ans + "\n");


        bw.flush();
    }

    public static void dfs(int[][] board, boolean[][] visited, int x, int y, int depth, int sum) {
        if (depth == 4) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + row[i];
            int ny = y + col[i];

            if (0 <= nx && nx < board.length && 0 <= ny && ny < board[0].length) {
                if (!visited[nx][ny]) {

                    if(depth == 2){
                        visited[nx][ny] = true;
                        dfs(board, visited, x, y, depth + 1, sum + board[nx][ny]);
                        visited[nx][ny] = false;
                    }
                    visited[nx][ny] = true;
                    dfs(board, visited, nx, ny, depth + 1, sum + board[nx][ny]);
                    visited[nx][ny] = false;
                }
            }
        }
    }
}