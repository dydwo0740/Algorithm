import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int N;
    static List<int[]>[] g;
    static String answer;

    static class Info{
        int v;
        int w;
        StringBuilder past = new StringBuilder();

        public Info(int v, int w, String str) {
            this.v = v;
            this.w = w;
            this.past.append(str);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());

        g = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            g[v1].add(new int[]{v2, dist});
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());


        int weight = dijkstra(start, end);

        bw.write(weight + "\n");
        bw.write(answer.split(" ").length + "\n");
        bw.write(answer + "\n");


        bw.flush();
        bw.close();
        br.close();
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));
        int[] dist = new int[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;

        pq.add(new Info(start, 0, String.valueOf(start)));

        while (!pq.isEmpty()) {
            Info cur = pq.poll();

            if (cur.w > dist[cur.v]) {
                continue;
            }

            for (int i = 0; i < g[cur.v].size(); i++) {
                int[] next = g[cur.v].get(i);

                if (dist[next[0]] > next[1] + cur.w) {
                    dist[next[0]] = next[1] + cur.w;
                    pq.add(new Info(next[0], dist[next[0]], cur.past.toString() + " " + next[0]));

                    if(next[0] == end){
                        answer = cur.past.toString() + " " + next[0];
                    }
                }
            }

        }

        return dist[end];
    }
}