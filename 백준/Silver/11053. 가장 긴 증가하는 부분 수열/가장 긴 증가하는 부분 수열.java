import java.io.*;
import java.util.*;

public class Main {

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] seq = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        int LIS = 0;
        for (int i = 0; i < N; i++) {
            int idx = binarySearch(seq[i], 0, LIS, LIS + 1);

            if(idx == -1){
                dp[LIS++] = seq[i];
            } else{
                dp[idx] = seq[i];
            }
        }

        bw.write(LIS + "\n");

        bw.flush();
    }

    public static int binarySearch(int num, int start, int end, int size) {
        int res = 0;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (num <= dp[mid]) {
                res = mid;
                end = mid - 1;
            } else{
                start = mid + 1;
            }
        }

        if (start == size) {
            return -1;
        }

        return res;
    }







}