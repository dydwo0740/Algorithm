import java.io.*;
import java.util.*;

public class Main {
    static int[][] board;
    static int[] row = {1, -1, 0, 0, 1, 1, -1, -1, 0};
    static int[] col = {0, 0, 1, -1, 1, -1, 1, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        board = new int[8][8];

        for (int i = 0; i < 8; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            String[] s = str.split("");
            for (int j = 0; j < 8; j++) {
                if (s[j].equals("#")) {
                    board[i][j] = -1;
                }
            }
        }

        bw.write((bfs() ? 1 : 0) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean bfs(){
        int depth = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{7, 0});

        while (!queue.isEmpty()) {
            Queue<int[]> temp = new LinkedList<>(queue);
            queue.clear();
            while (!temp.isEmpty()) {
                int[] pos = temp.poll();

                if (pos[0] == 0 && pos[1] == 7) {
                    return true;
                }

                if (board[pos[0]][pos[1]] == -1) {
                    continue;
                }

                for (int i = 0; i < 9; i++) {
                    int nx = pos[0] + row[i];
                    int ny = pos[1] + col[i];

                    if (0 <= nx && nx < 8 && 0 <= ny && ny < 8) {
                        if (board[nx][ny] == 0) {
                            queue.add(new int[]{nx, ny});
                        }
                    }
                }
            }
            down();
            depth++;

            if (depth == 9) {
                return true;
            }
        }

        return false;
    }

    public static void down(){
        for (int i = 7; i > 0; i--) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = board[i - 1][j];
            }
        }

        for (int j = 0; j < 8; j++) {
            board[0][j] = 0;
        }
    }

}
