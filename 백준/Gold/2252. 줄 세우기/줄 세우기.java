import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] lists;
    static int[] indegree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        lists = new ArrayList[N + 1];
        indegree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int head = Integer.parseInt(st.nextToken());
            int tail = Integer.parseInt(st.nextToken());

            indegree[tail]++;
            lists[head].add(tail);
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            bw.write(cur + " ");

            for (int i = 0; i < lists[cur].size(); i++) {
                int next = lists[cur].get(i);

                if (--indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }


        bw.flush();
        bw.close();
        br.close();
    }


}
