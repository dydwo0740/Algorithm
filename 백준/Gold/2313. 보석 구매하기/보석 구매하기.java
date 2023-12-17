import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int total = 0;
        int T = Integer.parseInt(reader.readLine());
        StringBuilder result = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(reader.readLine());
            String[] arrStr = reader.readLine().split(" ");
            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(arrStr[i]);
            }

            int mx = arr[0];
            int mxStart = 0;
            int mxEnd = 0;

            int start = 0;
            int end = 0;

            for (int i = 1; i < N; i++) {
                if (arr[i] >= arr[i - 1] + arr[i]) {
                    start = i;
                    end = i;
                } else {
                    arr[i] = arr[i - 1] + arr[i];
                    end = i;
                }

                if (arr[i] > mx) {
                    mx = arr[i];
                    mxEnd = end;
                    mxStart = start;
                } else if (arr[i] == mx && mxEnd - mxStart > end - start) {
                    mx = arr[i];
                    mxEnd = end;
                    mxStart = start;
                }
            }

            result.append((mxStart + 1)).append(" ").append((mxEnd + 1)).append("\n");
            total += mx;
        }

        System.out.println(total);
        System.out.print(result);
    }
}
