import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static int N;
    static int M;
    static char[][] board;

    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            char[] row = new StringTokenizer(br.readLine()).nextToken().toCharArray();
            board[i] = row;
        }

        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'S') {
                    startX = i;
                    startY = j;
                    board[i][j] = '.';
                } else if (board[i][j] == 'D') {
                    endX = i;
                    endY = j;
                }
            }
        }
        int ans = bfs(startX, startY, endX, endY);
        bw.write((ans == -1 ? "KAKTUS" : ans) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int bfs(int curX, int curY, int endX, int endY) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        queue.add(new int[]{curX, curY});
        int time = 0;
        visited[curX][curY] = true;

        while (!queue.isEmpty()) {
            Queue<int[]> temp = new LinkedList<>(queue);
            queue.clear();
            findPositions(board);
            while (!temp.isEmpty()) {
                int[] cur = temp.poll();

                if (cur[0] == endX && cur[1] == endY) {
                    return time;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + row[i];
                    int ny = cur[1] + col[i];

                    if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                        if (!visited[nx][ny] && (board[nx][ny] == '.' || board[nx][ny] == 'D')) {
                            visited[nx][ny] = true;
                            queue.add(new int[]{nx, ny});
                        }
                    }
                }
            }
            time++;
        }
        return -1;
    }

    public static void findPositions(char[][] board) {
        List<int[]> deleteList = new ArrayList<>();
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == '*') {

                    for (int k = 0; k < 4; k++) {
                        int nx = i + row[k];
                        int ny = j + col[k];
                        if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                            if (board[nx][ny] == '.' && !visited[nx][ny]) {
                                deleteList.add(new int[]{nx, ny});
                                visited[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }

        for (int[] loc : deleteList) {
            board[loc[0]][loc[1]] = '*';
        }
    }
}