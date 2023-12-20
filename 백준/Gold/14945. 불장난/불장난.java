import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] dp = new int[105][105];

        dp[2][1] = 2;

        for (int i = 3; i <= 100; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = (dp[i - 1][j] * 2 + dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 10007;
            }
        }

        int sum = 0;

        for (int i = 1; i < N; i++) {
            sum += dp[N][i];
        }

        bw.write(sum % 10007 + "\n");

        bw.flush();
    }


}