import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] seq = new int[N];
        int[] sum = new int[N];

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        sum[0] = seq[0];

        for (int i = 1; i < N; i++) {
            sum[i] += (sum[i - 1] + seq[i]);
        }

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            if (i >= 1) {
                ans = Math.max(ans, sum[i] - seq[0] + sum[N - 1] - sum[i - 1] - seq[N - 1]);
            }
        }

        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= N - 2; i++) {
            max = Math.max(max, -seq[i] + sum[N - 1] - sum[i]);
        }

        ans = Math.max(ans, max + sum[N - 1] - seq[0]);

        max = Integer.MIN_VALUE;

        for (int i = N - 2; i >= 1; i--) {
            max = Math.max(max, -seq[i] + sum[i - 1]);
        }

        ans = Math.max(ans, max + sum[N - 2]);

        bw.write(ans + "\n");


        bw.flush();
    }
}