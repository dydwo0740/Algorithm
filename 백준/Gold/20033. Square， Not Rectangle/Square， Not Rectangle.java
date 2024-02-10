import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] seq = new int[N];

        st = new StringTokenizer(br.readLine());

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
            min = Math.min(min, seq[i]);
            max = Math.max(max, seq[i]);
        }

        int ans = lowerBound(seq, 1, 1000000);
        bw.write(ans + "\n");
        bw.flush();
    }

    public static int lowerBound(int[] seq, int left, int right) {
        while (left < right) {
            int mid = (left + right + 1) / 2; // 이 부분에서 +1을 추가하여 left가 right보다 1 작을 때 바로 종료되도록 함
            
            if (isFind(mid, seq)) {
                left = mid;
            } else{
                right = mid - 1; // isFind 결과가 false일 경우, right를 줄임
            }
        }

        return left;
    }

    public static boolean isFind(int target, int[] seq) {
        int cnt = 0;
        for (int i = 0; i < seq.length; i++) {
            if (target <= seq[i]) {
                cnt++;
            } else{
                cnt = 0;
            }

            if (cnt >= target) {
                return true;
            }
        }

        return false;
    }
}
