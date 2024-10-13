import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator
                .comparingInt((int[] o) -> o[1])
                .thenComparing(o -> o[0]));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            pq.add(new int[]{s, e});
        }

        int time = 0;

        int ans = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (time > cur[0]) {
                continue;
            }

            ans++;
            time = cur[1];
        }

        bw.write(ans + "\n");


        bw.flush();
        bw.close();
        br.close();
    }

}
