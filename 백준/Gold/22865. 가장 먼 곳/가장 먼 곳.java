import java.io.*;
import java.util.*;

public class Main {
    static int A;
    static int B;
    static int C;

    static class Node{
        int v;
        int dist;

        public Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v=" + v +
                    ", dist=" + dist +
                    '}';
        }
    }

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        List<Node>[]g = new ArrayList[N+1];

        for(int i=1;i<=N;i++){
            g[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());

        B = Integer.parseInt(st.nextToken());

        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());

            int e = Integer.parseInt(st.nextToken());

            int cost = Integer.parseInt(st.nextToken());

            g[s].add(new Node(e, cost));
            g[e].add(new Node(s, cost));
        }

        for(int i=1;i<=N;i++){
            g[i].sort(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.v - o2.v;
                }
            });
        }

        int ans = Integer.MIN_VALUE;

        int idx = -1;

        int[] distA = getDist(g, A);
        int[] distB = getDist(g, B);
        int[] distC = getDist(g, C);

        for(int i=1;i<=N;i++){
            int max = Math.min(distA[i], Math.min(distB[i], distC[i]));
            if(ans < max){
                ans = max;
                idx = i;
            }
        }

        bw.write(idx+"\n");
        bw.flush();
    }

    public static int[] getDist(List<Node>[] g, int v){
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.dist - o2.dist;
            }
        });

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Node(v, 0));
        dist[v] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            //System.out.println("cur.toString() = " + cur.toString());
            if(cur.dist > dist[cur.v]){
                continue;
            }

            for(Node next : g[cur.v]){
                if(dist[next.v] > next.dist + dist[cur.v]){
                    //System.out.println("next = " + next+ " dist[next.v] = " + dist[next.v] + " " + dist[cur.v]);
                    dist[next.v] = next.dist + dist[cur.v];
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        return dist;
    }

}