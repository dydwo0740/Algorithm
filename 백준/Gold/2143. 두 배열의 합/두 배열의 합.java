import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] seqA = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            seqA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());

        int[] seqB = new int[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            seqB[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        for(int i=0;i<N;i++){
            int sum = 0;
            for(int j=i;j<N;j++){
                sum += seqA[j];
                listA.add(sum);
            }
        }
        for(int i=0;i<M;i++){
            int sum = 0;
            for(int j=i;j<M;j++){
                sum += seqB[j];
                listB.add(sum);
            }
        }

        listA.sort(Comparator.naturalOrder());
        listB.sort(Comparator.naturalOrder());

        int start = 0;
        int end = listB.size() - 1;

        long res = 0;

        while (start < listA.size() && end >= 0) {
            long sum = (long) listA.get(start) + (long) listB.get(end);

            if (sum == T) {
                int cntA = 0;
                int cntB = 0;
                int valueA = listA.get(start);
                int valueB = listB.get(end);
                while (start < listA.size() && valueA == listA.get(start)) {
                    start++;
                    cntA++;
                }
                while (end >= 0 && valueB == listB.get(end)) {
                    end--;
                    cntB++;
                }

                res += (long) cntA * (long) cntB;

            } else if (sum < T || end == -1) {
                start++;
            } else if (sum > T || start == listA.size()) {
                end--;
            }
        }

        bw.write(res + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    
}
