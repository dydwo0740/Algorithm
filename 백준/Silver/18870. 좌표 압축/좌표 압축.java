
import java.io.*;
import java.util.*;

public class Main {

    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] seq = new int[N];
        int[] origin = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            seq[i] = origin[i] = Integer.parseInt(st.nextToken());

        }

        int rank = 0;

        Arrays.sort(seq);

        for(int i=0;i<N;i++){
            if (!map.containsKey(seq[i])) {
                map.put(seq[i], rank++);
            }
        }


        for(int i=0;i<N;i++){
            bw.write(map.get(origin[i]) + " ");
        }

        bw.flush();



    }

}