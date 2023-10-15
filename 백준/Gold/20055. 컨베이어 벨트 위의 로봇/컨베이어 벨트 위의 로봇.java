import org.w3c.dom.Node;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static class Info{
        int num;
        int gold;
        int silver;
        int bronze;

        Info(int num, int gold, int silver, int bronze) {
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(st.nextToken());

        int size = N * 2;

        int[] seq = new int[size];

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<size;i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int step = 1;
        boolean[] visited = new boolean[N];

        while(true){
            rotate(seq, visited);
            move(seq, visited);
            if(seq[0] > 0){
                visited[0] = true;
                seq[0]--;
            }
            if(!check(seq, K)) {
                break;
            }
            step++;
        }

        bw.write(step + "\n");
        bw.flush();
    }

    public static boolean check(int[] seq, int K){
        int cnt = 0;

        for(int i=0;i<seq.length;i++){
            if(seq[i] == 0){
                cnt++;
            }
        }

        if(cnt >= K){
            return false;
        }

        return true;
    }

    public static void rotate(int[] seq, boolean[] visited){
        int temp = seq[seq.length - 1];
        for(int i=seq.length - 1;i>0;i--){
            seq[i] = seq[i - 1];
        }
        seq[0] = temp;

        for(int i=visited.length - 2;i>=0;i--){
            if(visited[i] && i == visited.length - 2){
                visited[i] = false;
                continue;
            }

            if(visited[i]){
                visited[i + 1] = true;
                visited[i] = false;
            }
        }
    }

    public static void move(int[] seq, boolean[] visited){
        for(int i=visited.length - 2;i>=0;i--){
            if(visited[i] && seq[i+1] > 0 && !visited[i+1]){
                visited[i] = false;
                seq[i + 1]--;
                visited[i + 1] = true;
            }
        }

        visited[visited.length - 1] = false;
    }
}