import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int[][] board;

    static int[] row = {-1, 1, 0, 0};
    static int[] col = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 1; j <= M; j++) {
                board[i][j] = str.charAt(j-1) - '0';
            }
        }

        int ans = bfs();

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int bfs() {

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][M + 1];

        queue.add(new int[]{1, 1});
        visited[1][1] = true;

        int dist = 1;

        while (!queue.isEmpty()) {
            Queue<int[]> temp = new LinkedList<>(queue);
            queue.clear();
            while (!temp.isEmpty()) {
                int[] cur = temp.poll();

                //System.out.println("Arrays.toString(cur) = " + Arrays.toString(cur) + " " + dist);

                if (cur[0] == N && cur[1] == M) {
                    return dist;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + row[i];
                    int ny = cur[1] + col[i];

                    if (1 <= nx && nx <= N && 1 <= ny && ny <= M) {
                        if (!visited[nx][ny] && board[nx][ny] == 1) {
                            queue.add(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
            dist++;
        }


        return -1;
    }

}
