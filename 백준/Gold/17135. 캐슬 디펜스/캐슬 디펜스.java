import java.io.*;
import java.util.*;

public class Main {
    static int ans = 0;
    static int[][] origin;

    static class Location {
        int x;
        int y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x && y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        int D = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][M];
        origin = new int[N + 1][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                origin[i][j] = map[i][j];
            }
        }

        permutation(map, new int[3], new boolean[M], 0, D);

        bw.write(ans+"\n");
        bw.flush();

    }

    public static void permutation(int[][] map, int[] output, boolean[] visited, int start, int D) {
        if (start == 3) {
            /*System.out.println("output[0] = " + output[0]);
            System.out.println("output[1] = " + output[1]);
            System.out.println("output[2] = " + output[2]);*/
            initialize(map, output, D);

            for(int i=0;i<origin.length;i++){
                for(int j=0;j<origin[0].length;j++){
                    map[i][j] = origin[i][j];
                }
            }
            return;
        }

        for (int i = start; i < map[0].length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[start] = i;
                permutation(map, output, visited, start + 1, D);
                //System.out.println("i + \" \" + start = " + i + " " + start);
                visited[i] = false;
            }
        }
    }

    public static void initialize(int[][] map, int[] output, int D) {
        int N = map.length;
        N--;
        int M = map[0].length;

        int answer = 0;

        List<Location> list = new ArrayList<>();
        Set<Location> deleteList = new HashSet<>();
        while (true) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[j][i] == 1) {
                        list.add(new Location(j, i));
                    }
                }
            }

            //System.out.println("list.size() = " + list.size());
            if (list.isEmpty()) {
                break;
            }

            for (int i = 0; i < 3; i++) {
                int x = N;
                int y = output[i];
                int min = Integer.MAX_VALUE;
                int findX = -1;
                int findY = -1;
                for (int j = 0; j < list.size(); j++) {
                    Location location = list.get(j);

                    int value = Math.abs(x - location.x) + Math.abs(y - location.y);
                    if (value <= D && min > value) {
                        min = value;
                        findX = location.x;
                        findY = location.y;
                    }
                }

                if(findX != -1) {
                    deleteList.add(new Location(findX, findY));
                }
            }

            for (Location location : deleteList) {
                map[location.x][location.y] = 0;
                //System.out.println("location = " + location.x + " " + location.y);
                answer++;
            }

            deleteList.clear();



            for(int i = N - 1;i>=0;i--){
                for(int j=0;j<M;j++) {
                    if (i == N - 1) {
                        map[i][j] = 0;
                    }else{
                        map[i + 1][j] = map[i][j];
                        map[i][j] = 0;
                    }
                }
            }

            /*for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println("answer = " + answer);*/


            list.clear();
        }

        ans = Math.max(ans, answer);


    }
}