import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N;

    static class Node {
        int v;
        int time;

        public Node(int v, int time) {
            this.v = v;
            this.time = time;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        List<Integer>[] g = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            while (true) {
                int v = Integer.parseInt(st.nextToken());
                if (v == 0) {
                    break;
                }
                g[i].add(v);
            }
        }

        for (int i = 1; i <= N; i++) {
            g[i].sort(Comparator.naturalOrder());
        }

        st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());

        List<Integer> startList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int v = Integer.parseInt(st.nextToken());
            startList.add(v);
        }

        int[] ans = bfs(startList, g, new int[N + 1]);

        for (int i = 1; i <= N; i++) {
            bw.write(ans[i] + " ");
        }


        bw.flush();

    }

    public static int[] bfs(List<Integer> startList, List<Integer>[] g, int[] ans) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        int[] count = new int[N + 1];

        Arrays.fill(ans, -1);

        for (Integer node : startList) {
            queue.add(new Node(node, 0));
            ans[node] = 0;
            visited[node] = true;
        }

        for(int i=1;i<=N;i++){
            count[i] = g[i].size() / 2 + ((g[i].size() % 2 == 0) ? 0 : 1);
        }


        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            List<Integer> passList = new ArrayList<>();

            for (int i = 0; i < g[cur.v].size(); i++) {
                int next = g[cur.v].get(i);
                count[next] -= 1;
                if (!visited[next]) {
                    if (count[next] <= 0) {
                        queue.add(new Node(next, cur.time + 1));
                        passList.add(next);
                        ans[next] = cur.time + 1;
                    }
                }
            }

            for (Integer v : passList) {
                visited[v] = true;
            }
        }

        return ans;
    }



}