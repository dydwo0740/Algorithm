import java.io.*;
import java.util.*;

public class Main {
    static int[][] seq;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        ans = 0;
        seq = new int[N][3]; // num, strike, ball

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            seq[i][0] = Integer.parseInt(st.nextToken());
            seq[i][1] = Integer.parseInt(st.nextToken());
            seq[i][2] = Integer.parseInt(st.nextToken());
        }

        dfs(new int[3], new boolean[10], 0);

        bw.write(ans + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int[] output, boolean[] visited, int depth) {
        if (depth == 3) {
            boolean flag = true;
            for (int i = 0; i < seq.length; i++) {
                int[] result = getResult(i, output);

                if (result[0] == seq[i][1] && result[1] == seq[i][2]) {

                } else {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                ans++;
            }
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (!visited[i]) {
                output[depth] = i;
                visited[i] = true;
                dfs(output, visited, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static int[] getResult(int index, int[] output) {
        String number = String.valueOf(seq[index][0]);
        String comp = String.valueOf(output[0] * 100 + output[1] * 10 + output[2]);


        boolean[] visitedA = new boolean[3];
        boolean[] visitedB = new boolean[3];

        int sCnt = 0;
        int bCnt = 0;

        for (int i = 0; i < 3; i++) {
            if (number.charAt(i) == comp.charAt(i)) {
                sCnt++;
                visitedA[i] = true;
                visitedB[i] = true;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    continue;
                }
                if (number.charAt(i) == comp.charAt(j) && !visitedA[i] && !visitedB[j]) {
                    bCnt++;
                    visitedA[i] = true;
                    visitedB[j] = true;
                }
            }
        }

//        if (comp.equals("324")) {
//            System.out.println(number);
//            System.out.println("sCnt = " + sCnt);
//            System.out.println("bCnt = " + bCnt);
//        }

        return new int[]{sCnt, bCnt};
    }

}
