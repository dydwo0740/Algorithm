import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {


    static int[][][] arr = {
            {{0,0},{0,1},{0,2},{0,3}}, // ㅡ
            {{0,0},{1,0},{2,0},{3,0}}, // ㅣ
            {{0,0},{0,1},{1,1},{1,2}}, // ㄹ
            {{0,1},{1,0},{1,1},{2,0}},
            {{0,0},{0,1},{0,2},{1,2}}, // ㄱ
            {{0,0},{0,1},{1,0},{2,0}},
            {{0,0},{1,0},{1,1},{1,2}},
            {{0,1},{1,1},{2,1},{2,0}},
            {{0,0},{0,1},{1,0},{1,1}}, // ㅁ
            {{0,0},{1,0},{1,1},{2,0}}, // ㅏ
            {{0,1},{1,0},{1,1},{2,1}}, // ㅓ
            {{0,1},{1,0},{1,1},{1,2}}, // ㅗ
            {{0,0},{0,1},{0,2},{1,1}} // ㅜ
    };

    static int[][] board;
    static int N;
    static int max = Integer.MIN_VALUE;
    static int test = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            if (N == 0) {
                break;
            }

            max = -987654321;

            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    check(i, j);
                }
            }
            
            bw.write(test + ". " + max + "\n");
            test++;
        }

        bw.flush();
    }

    public static void check(int x, int y) {
        boolean flag = true;
        int sum = 0;
        for (int k = 0; k < 13; k++) {
            flag = true;
            sum = 0;
            for (int i = 0; i < 4; i++) {
                int nx = x + arr[k][i][0];
                int ny = y + arr[k][i][1];
                if (inside(nx, ny)) {
                    flag = false;
                    break; // 그모양 스킵
                }
                sum += board[nx][ny];
            }

            if (flag) max = Math.max(sum, max);
        }
    }

    public static boolean inside(int nx, int ny) { return (nx < 0 || ny < 0 || nx >= N || ny >= N); }


}