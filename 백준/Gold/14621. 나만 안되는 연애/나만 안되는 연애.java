import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static char[] ch;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ch = new char[N + 1];
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            String type = st.nextToken();
            ch[i] = type.charAt(0);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt((int[] o) -> o[2]));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.add(new int[]{v1, v2, w});
        }

        int total = 0;
        int depth = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (getParent(cur[0]) == getParent(cur[1])) {
                continue;
            }

            if (ch[cur[0]] != ch[cur[1]]) {
                union(cur[0], cur[1]);
                total += cur[2];
                depth++;
            }
        }

        if (depth == N - 1) {
            bw.write(total + "\n");
        } else{
            bw.write(-1 + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int getParent(int x) {
        if (parent[x] == x) {
            return parent[x];
        }
        return parent[x] = getParent(parent[x]);
    }

    public static void union(int x, int y) {
        x = getParent(x);
        y = getParent(y);

        if (x < y) {
            parent[y] = x;
        } else{
            parent[x] = y;
        }
    }


}
