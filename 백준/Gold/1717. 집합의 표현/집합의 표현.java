import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (type == 0) {
                union(x, y);
            } else{
                if (getParent(x) == getParent(y)) {
                    bw.write("YES\n");
                } else{
                    bw.write("NO\n");
                }
            }
        }



        bw.flush();
        bw.close();
        br.close();
    }


    public static int getParent(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = getParent(parent[x]);
    }

    public static void union(int x, int y) {
        x = getParent(x);
        y = getParent(y);

        if (x == y) {
            return;
        }

        if (x < y) {
            parent[y] = x;
        } else{
            parent[x] = y;
        }
    }


}
