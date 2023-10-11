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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬을 센다? (BFS)

        boolean[][] visited = new boolean[N][N];
        int index = 1;
        Map<Integer, List<Location>> map = new HashMap<>();

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(board[i][j] == 1 && !visited[i][j]){
                    initialize(i, j, board, visited, map, index);
                    index++;
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        for(int i=1;i<index;i++){
            for(int j=1;j<index;j++){
                if(i==j){
                    continue;
                }
                answer = Math.min(answer, check(i, j, map));
            }
        }



        bw.write(answer+"\n");
        bw.flush();

    }

    public static void initialize(int x, int y, int[][] board, boolean[][] visited, Map<Integer, List<Location>> map, int index){
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(x, y));
        visited[x][y] = true;
        List<Location> list = new ArrayList<>();
        list.add(new Location(x, y));

        int N = board[0].length;

        while (!queue.isEmpty()) {
            Location location = queue.poll();

            int curX = location.x;
            int curY = location.y;

            for(int i=0;i<4;i++){
                int nx = curX + row[i];
                int ny = curY + col[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if(!visited[nx][ny] && board[nx][ny] == 1){
                        queue.add(new Location(nx, ny));
                        list.add(new Location(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }

        }
        map.put(index, list);
    }

    public static int check(int comp1, int comp2, Map<Integer, List<Location>> map) {
        int answer = Integer.MAX_VALUE;

        List<Location> compareList1 = map.get(comp1);
        List<Location> compareList2 = map.get(comp2);

        for (Location start : compareList1) {
            for (Location end : compareList2) {
                answer = Math.min(answer, Math.abs(start.x - end.x) + Math.abs(start.y - end.y));
            }
        }

        return answer - 1;
    }
}