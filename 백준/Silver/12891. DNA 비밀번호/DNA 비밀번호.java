import java.io.*;
import java.util.*;

public class Main {
    static int M;
    static int[] count = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        String str = st.nextToken();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            count[i] = Integer.parseInt(st.nextToken());
        }

        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);

        int[] status = new int[4];

        int left = 0;
        int right = M - 1;
        for (int i = left; i <= right; i++) {
            if (map.containsKey(str.charAt(i))) {
                status[map.get(str.charAt(i))]++;
            }
        }
        int ans = 0;
        while (true) {
            if (isAccurate(status, left, right)) {
                ans++;
            }

            if (left == str.length() - M) {
                break;
            }

            if (map.containsKey(str.charAt(left))) {
                status[map.get(str.charAt(left))]--;
            }
            if (map.containsKey(str.charAt(right + 1))) {
                status[map.get(str.charAt(right + 1))]++;
            }
            left++;
            right++;
        }
        bw.write(ans + "\n");


        bw.flush();
    }

    public static boolean isAccurate(int[] status, int left, int right) {
        for (int i = 0; i < 4; i++) {
            //System.out.println("status[i] + \" \" + count[i] = " + status[i] + " " + count[i]);
            if (status[i] < count[i]) {
                return false;
            }
        }
        return true;
    }


}