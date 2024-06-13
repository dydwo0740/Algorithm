import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        int[] dp = new int[12];

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = dp[0] + dp[1] + dp[2];
        dp[4] = dp[1] + dp[2] + dp[3];

        for (int i = 5; i <= 11; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }


        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());

            bw.write(dp[num] + "\n");
        }


        bw.flush();
        bw.close();
        br.close();
    }


}
