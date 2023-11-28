import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        int[] bag = new int[M];
        int[][] item = new int[N + 1][2];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());

            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            item[i][0] = W;
            item[i][1] = V;
        }

        int max = -1;

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            bag[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, bag[i]);
        }



        int[][] dp = new int[N + 1][max + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= max; j++) {
                if (item[i][0] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item[i][0]] + item[i][1]);
                }
            }
        }
        int ans = 1;
        int Most_WE = bag[0];
        int Most_VA = -1;
        for (int i = 1; i <= N; i++) {
            Most_VA = Math.max(Most_VA, dp[i][bag[0]]);
        }
        for (int i = 2; i < M; i++) {
            int VA = 0;
            for (int j = 1; j <= N; j++) {
                VA = Math.max(VA, dp[j][bag[i]]);
            }
            if ((long)Most_VA * (long)bag[i] < (long)VA * (long)Most_WE) {
                Most_VA = VA;
                Most_WE = bag[i];
                ans = i + 1;
            }
        }

        bw.write(ans + "\n");

        bw.flush();

    }



}