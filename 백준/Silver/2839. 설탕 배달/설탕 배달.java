import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[5001];

        Arrays.fill(dp, -1);

        dp[3] = 1;
        dp[5] = 1;

        for (int i = 6; i <= 5000; i++) {
            if (dp[i - 5] == -1 && dp[i - 3] == -1) {
                continue;
            }

            if (dp[i - 5] > 0 && dp[i - 3] > 0) {
                dp[i] = Math.min(dp[i - 5], dp[i - 3]) + 1;
            } else if (dp[i - 5] > 0) {
                dp[i] = dp[i - 5] + 1;
            } else {
                dp[i] = dp[i - 3] + 1;
            }
        }

        bw.write(dp[N] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

}
