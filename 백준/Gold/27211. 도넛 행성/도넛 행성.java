import java.io.*;
import java.util.*;

public class Main {

    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};

    static int N;
    static int M;

    static class Location{
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[N][M];

        int count = 0;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if (board[i][j] == 0 && !visited[i][j]) {
                    bfs(board, visited, i, j);
                    count++;
                }
            }
        }

        bw.write(count + "\n");

        bw.flush();
    }

    public static void bfs(int[][] board, boolean[][] visited, int x, int y){
        Queue<Location> queue = new LinkedList<>();

        queue.add(new Location(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Location location = queue.poll();

            for(int i=0;i<4;i++){
                int nx = location.x + row[i];
                int ny = location.y + col[i];

                if(nx == N){
                    nx = 0;
                }
                if(nx == -1){
                    nx = N - 1;
                }

                if(ny == M){
                    ny = 0;
                }

                if(ny == -1){
                    ny = M - 1;
                }
                if(board[nx][ny] == 0) {
                    if (!visited[nx][ny]) {
                        queue.add(new Location(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

}