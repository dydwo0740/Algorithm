import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int H;
    static int W;
    static int X;
    static int Y;

    static int N;
    static int M;

    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());


        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());


        N = H + X;
        M = W + Y;

        int[][] after = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                after[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] answer = new int[H][W];

        for(int i=0;i<X;i++){
            for (int j = 0; j < W; j++) {
                answer[i][j] = after[i][j];
            }
        }

        for (int i = X; i < H; i++) {
            for(int j=0;j<Y;j++){
                answer[i][j] = after[i][j];
            }
        }

        for (int i = X; i < H; i++) {
            for (int j = Y; j < W; j++) {
                answer[i][j] = after[i][j] - answer[i - X][j - Y];
            }
        }


        for(int i=0;i<H;i++){
            for (int j = 0; j < W; j++) {
                bw.write(answer[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}