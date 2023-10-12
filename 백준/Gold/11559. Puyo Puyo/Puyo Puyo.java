import java.io.*;
import java.util.*;

public class Main {

    static class Location{
        int x;
        int y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        char[][] board = new char[12][6];
        boolean[][] visited = new boolean[12][6];

        int answer = 0;

        for(int i=0;i<12;i++){
            st = new StringTokenizer(br.readLine());
            board[i] = st.nextToken().toCharArray();
        }

        while (true) {
            boolean flag = false;

            for(int i=0;i<12;i++){
                Arrays.fill(visited[i], false);
            }


            for(int i=0;i<12;i++){
                for(int j=0;j<6;j++){
                    if (board[i][j] != '.' && !visited[i][j]) {
                        if (check(board, visited, i, j)) {
                            flag = true;
                        }
                    }
                }
            }

            if(!flag){
                break;
            }

            answer++;

            // 이동

            initialize(board);

            /*for(int i=0;i<12;i++){
                for(int j=0;j<6;j++){
                    bw.write(board[i][j] + " ");
                }
                bw.write("\n");
            }*/
        }

        bw.write(answer+"\n");

        bw.flush();

    }

    public static boolean check(char[][] board, boolean[][] visited, int x, int y){
        Queue<Location> queue = new LinkedList<>();

        List<Location> deleteList = new ArrayList<>();

        visited[x][y] = true;
        queue.add(new Location(x, y));
        deleteList.add(new Location(x, y));

        while (!queue.isEmpty()) {
            Location location = queue.poll();

            int curX = location.x;
            int curY = location.y;

            for(int i=0;i<4;i++){
                int nx = curX + row[i];
                int ny = curY + col[i];

                if (0 <= nx && nx < 12 && 0 <= ny && ny < 6) {
                    if (!visited[nx][ny] && board[nx][ny] == board[x][y]) {
                        queue.add(new Location(nx, ny));
                        visited[nx][ny] = true;
                        deleteList.add(new Location(nx, ny));
                    }
                }
            }

        }

        if (deleteList.size() >= 4) {
            for (Location delete : deleteList) {
                board[delete.x][delete.y] = '.';
            }
            return true;
        }

        return false;
    }

    public static void initialize(char[][] board){
        while(true) {
            int move = 0;
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] != '.') {
                        if (board[i + 1][j] == '.') {
                            board[i + 1][j] = board[i][j];
                            board[i][j] = '.';
                            move++;
                        }
                    }
                }
            }

            if(move == 0){
                break;
            }
        }
    }
}