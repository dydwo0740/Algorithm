import java.io.*;
import java.util.*;

public class Main {
    static int homeX;
    static int homeY;

    static int res = Integer.MAX_VALUE;

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Point{
        int x;

        int y;
        int cost;

        public Point(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, -1, 1};

    static Map<Node, int[][]> map = new HashMap<>();

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

                if(board[i][j] == -1){
                    homeX = i;
                    homeY = j;
                    board[i][j] = 0;
                }
            }
        }

        List<Node> people = new ArrayList<>();


        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++) {
                if (board[i][j] == 0) {
                    people.add(new Node(i, j));
                    int[][] dist = dijkstra(board, i, j);
                    map.put(new Node(i, j), dist);
                }
            }
        }

        boolean[] visited = new boolean[people.size()];

        permutation(new ArrayList<>(), people, visited);

        bw.write(res+"\n");

        bw.flush();
    }

    public static int[][] dijkstra(int[][] board, int x, int y){
        int N = board.length;

        int[][] dist = new int[N][N];

        for(int i=0;i<N;i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.cost - o2.cost;
            }
        });

        queue.add(new Point(x, y, 0));
        dist[x][y] = 0;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            if(dist[cur.x][cur.y] < cur.cost){
                continue;
            }

            for(int i=0;i<4;i++){
                int nx = cur.x + row[i];
                int ny = cur.y + col[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if(dist[nx][ny] > cur.cost + board[nx][ny]){
                        dist[nx][ny] = cur.cost + board[nx][ny];
                        queue.add(new Point(nx, ny, dist[nx][ny]));
                    }
                }
            }
        }

        return dist;
    }


    public static void permutation(List<Node> list, List<Node> people, boolean[] visited){
        if(list.size() == people.size()){
            initialize(list);
            return;
        }

        for(int i=0;i<people.size();i++){
            if(!visited[i]){
                visited[i] = true;
                Node node = people.get(i);
                list.add(new Node(node.x, node.y));
                permutation(list, people, visited);
                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    public static void initialize(List<Node> list){
        int curX = homeX;
        int curY = homeY;

        int ans = 0;

        for (Node next : list) {
            int[][] dist = map.get(next);

            ans += dist[curX][curY];

            curX = next.x;
            curY = next.y;
        }

        int[][] dist = map.get(new Node(curX, curY));

        ans += dist[homeX][homeY];

        res = Math.min(res, ans);
    }

}