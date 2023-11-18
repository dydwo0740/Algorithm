import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] board = new int[N + 1][N + 1];
        int[][][] dp = new int[N + 1][N + 1][3];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int currentMilk = board[i][j];    // 현재 위치의 우유

                // 우유 마시는 순서: 딸기(0) -> 초코(1) -> 바나나(2)
                if (currentMilk == 0) {
                    // 이전 순서의 바나나(2)를 최근으로 마신 개수 + 1
                    dp[i][j][0] = Math.max(dp[i][j - 1][2] + 1, dp[i - 1][j][2] + 1);
                } else {
                    dp[i][j][0] = Math.max(dp[i][j - 1][0], dp[i - 1][j][0]);
                }

                // 추가 조건식: 우유 마시는 순서 지키기 위함 (해당 현재 우유 currentMilk 를 마셔도 되는지 확인)
                if (currentMilk == 1 &&
                        dp[i][j][0] > dp[i][j][1]) {    // 이전에 딸기(0)를 먹었는지
                    // 이전 순서의 딸기(0)를 최근으로 마신 개수 + 1
                    dp[i][j][1] = Math.max(dp[i][j - 1][0] + 1, dp[i - 1][j][0] + 1);
                } else {
                    dp[i][j][1] = Math.max(dp[i][j - 1][1], dp[i - 1][j][1]);
                }

                if (currentMilk == 2 &&
                        dp[i][j][1] > dp[i][j][2]) {    // 이전에 초코(1)를 먹었는지
                    // 이전 순서의 초코(1)를 최근으로 마신 개수 + 1
                    dp[i][j][2] = Math.max(dp[i][j - 1][1] + 1, dp[i - 1][j][1] + 1);
                } else {
                    dp[i][j][2] = Math.max(dp[i][j - 1][2], dp[i - 1][j][2]);
                }
            }
        }

        int ans = 0;

        for (int k = 0; k < 3; k++) {
            ans = Math.max(ans, dp[N][N][k]);
        }


        bw.write(ans + "\n");
        bw.flush();
    }


}