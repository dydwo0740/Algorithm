import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int v1;
        int v2;
        int w;

        public Node(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }
    }
    static int[] parent;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.add(new Node(v1, v2, w));
        }
        int ans = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (getParent(node.v1) == getParent(node.v2)) {
                continue;
            }

            union(node.v1, node.v2);
            ans += node.w;
        }

        bw.write(ans + "\n");

        bw.flush();
    }

    public static int getParent(int x) {
        if(parent[x] == x){
            return x;
        }
        return parent[x] = getParent(parent[x]);
    }

    public static void union(int x, int y) {
        x = getParent(x);
        y = getParent(y);

        if(x == y){
            return;
        }

        if(x < y){
            parent[y] = x;
        } else{
            parent[x] = y;
        }
    }

}