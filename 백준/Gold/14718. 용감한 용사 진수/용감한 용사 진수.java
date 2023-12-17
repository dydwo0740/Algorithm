import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int K;
    static int[][] value;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        value = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            value[i][0] = a;
            value[i][1] = b;
            value[i][2] = c;
        }

        permutation(new int[3], 0);

        bw.write(min+"\n");


        bw.flush();
    }

    public static void permutation(int[] output, int depth) {
        if(depth == 3){
            solve(output);
            return;
        }

        for (int i = 0; i < N; i++) {
            output[depth] = i;
            permutation(output, depth + 1);
        }
    }

    public static void solve(int[] output) {
        int cnt = 0;
        int sum = value[output[0]][0] + value[output[1]][1] + value[output[2]][2];

        for (int i = 0; i < N; i++) {
            if (value[i][0] <= value[output[0]][0] && value[i][1] <= value[output[1]][1] && value[i][2] <= value[output[2]][2]) {
                cnt++;
            }
        }

        if (cnt >= K) {
            min = Math.min(min, sum);
        }
    }



}