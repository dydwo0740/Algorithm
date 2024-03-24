import java.util.*;
import java.io.*;

public class Main {
    static int n, m, result, start, end;
    static List<int[]>[] al;
    static int[] dist;
    static List<Integer>[] route;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new int[n + 1];
        al = new ArrayList[n + 1];
        route = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            al[i] = new ArrayList<>();
            route[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            al[a].add(new int[]{b, c});
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{start, 0});
        dist[start] = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            //System.out.println(Arrays.toString(cur));

            if (dist[cur[0]] < cur[1]) continue;

            for (int[] next : al[cur[0]]) {
                if (dist[next[0]] > cur[1] + next[1]) {
                    dist[next[0]] = cur[1] + next[1];
                    route[next[0]].clear(); // 일단 이렇게
                    route[next[0]].add(cur[0]);
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }
        System.out.println(dist[end]);
        int cur = end;
        List<Integer> result = new ArrayList<>();
        while (cur != start) {
            result.add(cur);
            cur = route[cur].get(0);
        }

        result.add(start);


        System.out.println(result.size());

        for (int i = result.size() - 1; i>= 0;i--) {
            System.out.print(result.get(i) + " ");
        }
    }

}