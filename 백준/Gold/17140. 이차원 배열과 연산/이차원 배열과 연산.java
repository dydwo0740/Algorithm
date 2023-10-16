import java.io.*;
import java.util.*;

public class Main {

    static class Info{
        int key;
        int value;

        Info(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    static int N = 3;
    static int M = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());

        int y = Integer.parseInt(st.nextToken());

        int value = Integer.parseInt(st.nextToken());


        int[][] board = new int[100][100];

        for(int i=0;i<3;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int age = -1;

        while(++age <= 100){
            if(board[x-1][y-1] == value){
                break;
            }
            initialize(board);

            /*for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    System.out.print(board[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();*/
        }

        if(age == 101){
            age = -1;
        }

        bw.write(age+"\n");
        bw.flush();
    }

    public static void initialize(int[][] board) {
        int len = -1;
        PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                if (o1.value == o2.value) {
                    return o1.key - o2.key;
                }
                return o1.value - o2.value;
            }
        });

        if(N >= M){
            int[] count = new int[101];

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    count[board[i][j]]++;
                }

                for(int k=1;k<=100;k++){
                    if(count[k] > 0){
                        pq.add(new Info(k, count[k]));
                    }
                }
                int index = 0;
                while(!pq.isEmpty()){
                    Info info = pq.poll();

                    board[i][index++] = info.key;
                    board[i][index++] = info.value;
                    len = Math.max(index, len);
                    if(index == 100){
                        break;
                    }
                }

                Arrays.fill(count, 0);
                pq.clear();
                for(;index <100;index++){
                    board[i][index] = 0;
                }
            }

            M = len;
            return;
        }

        int[] count = new int[101];

        for(int j=0;j<M;j++){
            for(int i=0;i<N;i++){
                count[board[i][j]]++;
                board[i][j] = 0;
            }

            for(int k=1;k<=100;k++){
                if(count[k] > 0){
                    pq.add(new Info(k, count[k]));
                }
            }
            int index = 0;
            while(!pq.isEmpty()){
                Info info = pq.poll();

                board[index++][j] = info.key;
                board[index++][j] = info.value;
                len = Math.max(index, len);
                if(index == 100){
                    break;
                }
            }

            Arrays.fill(count, 0);
            pq.clear();
            for(;index <100;index++){
                board[index][j] = 0;
            }
        }
        N = len;
    }
}