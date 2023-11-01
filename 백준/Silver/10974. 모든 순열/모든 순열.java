import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // R은 1 B도 1이라고 가정합니다.

        int N = Integer.parseInt(st.nextToken());


        boolean[] visited = new boolean[N + 1];
        int[] output = new int[N];

        permutation(output, visited, 0, N);

        bw.flush();
    }

    public static void permutation(int[] output, boolean[] visited, int depth, int N) {
        if (depth == N) {
            for (int i = 0; i < N; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = i;
                permutation(output, visited, depth + 1, N);
                visited[i] = false;
            }
        }
    }

}