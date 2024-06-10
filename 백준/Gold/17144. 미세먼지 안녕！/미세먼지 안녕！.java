import org.w3c.dom.Node;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        int time = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        int x = -1;
        int y = -1;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    x = i;
                    y = j;
                }
            }
        }

        for(int i=0;i<time;i++){
            // 먼지의 확산
            airPollute(map, N, M);
           // printAir(map, N, M);
            airClean(x - 1, x, map, N, M);
            //printAir(map, N, M);
        }

        int answer = 0;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                //bw.write(map[i][j]+" ");
                if(map[i][j] > 0){
                    answer += map[i][j];
                }
            }
            //bw.write("\n");
        }

        bw.write(answer+"\n");
        bw.flush();

    }

    public static void airPollute(int[][] map, int N, int M){
        int[][] temp = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] > 0){
                    int sum = 0;
                    for(int s=0;s<4;s++){
                        int nx = i + row[s];
                        int ny = j + col[s];
                        if(0<=nx && nx < N && 0<= ny && ny < M) {
                            if (map[nx][ny] != -1) {
                                sum += (map[i][j] / 5);
                                temp[nx][ny] += (map[i][j] / 5);
                            }
                        }
                    }

                    temp[i][j] += -sum;

                }
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                //System.out.print(temp[i][j] + " ");
                map[i][j] += temp[i][j];
            }
            //System.out.println();
        }
    }

    public static void airClean(int x1, int x2, int[][] map, int N, int M){
        // 오른쪽
        int temp = 0;
        for(int i=0;i<M-1;i++){
            int tmp = map[x1][i+1];
            map[x1][i+1] = temp;
            temp = tmp;
        }

        //위쪽
        for(int i=x1;i>0;i--){
            int tmp = map[i - 1][M-1];
            map[i-1][M-1] = temp;
            temp = tmp;
        }

        //왼쪽
        for(int i=M-1;i>0;i--){
            int tmp = map[0][i - 1];
            map[0][i-1] = temp;
            temp = tmp;
        }

        //아래쪽
        for(int i=0;i<x1 - 1;i++){
            int tmp = map[i+1][0];
            map[i+1][0] = temp;
            temp = tmp;
        }

        temp = 0;
        //오른쪽
        for(int i=0;i<M-1;i++){
            int tmp = map[x2][i+1];
            map[x2][i+1] = temp;
            temp = tmp;
        }

        //아래쪽
        for(int i=x2;i<N-1;i++){
            int tmp = map[i+1][M-1];
            map[i+1][M-1] = temp;
            temp = tmp;
        }
        //왼쪽
        for(int i=M-1;i>0;i--){
            int tmp = map[N-1][i - 1];
            map[N-1][i-1] = temp;
            temp = tmp;
        }
       // System.out.println("x2 = " + x2);
        //System.out.println("N-1 = " + (N - 1));
        //위쪽
        for(int i=N-1;i>x2 + 1;i--){
            int tmp = map[i-1][0];
            map[i-1][0] = temp;
            temp = tmp;
        }

    }

    public static void printAir(int[][] map, int N, int M){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}