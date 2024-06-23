import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<long[]>[] g;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        g = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            g[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            g[e].add(new long[]{s, c});
        }

        st = new StringTokenizer(br.readLine());

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong((long[] o) -> o[1]));

        for (int i = 0; i < K; i++) {
            long num = Long.parseLong(st.nextToken());

            pq.add(new long[]{num, 0});
            dist[(int) num] = 0L;
        }


        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            if (cur[1] > dist[(int) cur[0]]) {
                continue;
            }

            for (int i = 0; i < g[(int) cur[0]].size(); i++) {
                long[] next = g[(int) cur[0]].get(i);

                if (cur[1] + next[1] < dist[(int) next[0]]) {
                    dist[(int) next[0]] = cur[1] + next[1];

                    pq.add(new long[]{next[0], dist[(int) next[0]]});
                }
            }
        }

        int index = 0;
        long ans = 0;

        for (int i = 1; i <= N; i++) {
            if (ans < dist[i]) {
                ans = dist[i];
                index = i;
            }
        }

        bw.write(index + "\n");
        bw.write(ans + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
