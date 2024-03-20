import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Queue<Integer> bridge = new LinkedList<>();
        Queue<Integer> truck = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            int weight = Integer.parseInt(st.nextToken());

            truck.add(weight);
        }

        for (int i = 0; i < W; i++) {
            bridge.add(0);
        }

        int total = 0;

        int ans = 0;

        while (!truck.isEmpty()) {
            if(total + truck.peek() - bridge.peek() <= L){
                int weight = bridge.poll();
                total -= weight;
                total += truck.peek();
                bridge.add(truck.poll());
            } else{
                int weight = bridge.poll();
                total -= weight;
                bridge.add(0);
            }

            ans++;
        }

        bw.write((ans + W) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}