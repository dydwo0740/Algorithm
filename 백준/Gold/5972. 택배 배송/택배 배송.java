import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static int N;
    static List<Node>[] g;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        g = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            g[v1].add(new Node(v2, weight));
            g[v2].add(new Node(v1, weight));
        }

        int ans = dijkstra();

        bw.write(ans+"\n");

        bw.flush();
        br.close();
        bw.close();
    }

    public static int dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });

        pq.add(new int[]{1, 0});

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[1] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[1] > dist[cur[0]]) {
                continue;
            }

            for (int i = 0; i < g[cur[0]].size(); i++) {
                Node next = g[cur[0]].get(i);
                if (dist[next.v] > cur[1] + next.w) {
                    dist[next.v] = cur[1] + next.w;
                    pq.add(new int[]{next.v, dist[next.v]});
                }
            }
        }

        return dist[N] == Integer.MAX_VALUE ? -1 : dist[N];
    }
}