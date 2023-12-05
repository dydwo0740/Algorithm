import java.io.*;

import java.util.*;

public class Main{
    static Map<Integer, PriorityQueue<Integer>> map;
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int W = Integer.parseInt(st.nextToken());

        List<Integer> three = new ArrayList<>();
        List<Integer> five = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int sweet = Integer.parseInt(st.nextToken());

            if(type == 3){
                three.add(sweet);
                continue;
            }

            five.add(sweet);
        }

        Collections.sort(three, Comparator.reverseOrder());
        Collections.sort(five, Comparator.reverseOrder());

        long[] threeSum = new long[three.size() + 1];
        long[] fiveSum = new long[five.size() + 1];

        for (int i = 1; i <= three.size(); i++) {
            threeSum[i] = threeSum[i - 1] + three.get(i - 1);
        }

        for (int i = 1; i <= five.size(); i++) {
            fiveSum[i] = fiveSum[i - 1] + five.get(i - 1);
        }

        int idx = Math.min(three.size(), W / 3);
        long sum = 0;

        while(idx >= 0){
            int fiveIdx = Math.min((W - 3 * idx) / 5, five.size());

            long temp = threeSum[idx] + fiveSum[fiveIdx];

            sum = Math.max(sum, temp);

            idx--;
        }

        bw.write(sum+"\n");


        bw.flush();
    }


}