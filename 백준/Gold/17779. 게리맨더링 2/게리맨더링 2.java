import java.io.*;
import java.util.*;

public class Main {

    static int N;

    static int total = 0;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                total += board[i][j];
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                setXAndY(board, i, j);
            }
        }

        bw.write(answer + "\n");

        bw.flush();
    }

    public static void setXAndY(int[][] board, int x, int y) {


        //가능한 d1, d2의 쌍?

        for(int i=1;i<N;i++){
            for(int j=1;j<N;j++){
                if(x + i + j >= N){
                    continue;
                }
                if(y - i < 1 || y + j >= N){
                    continue;
                }
                answer = Math.min(answer, divide(board, x, y, i, j));

            }
        }
    } //1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N

    public static int divide(int[][] board, int x, int y, int d1, int d2){

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;

        boolean[][] visited = new boolean[N + 1][N + 1];

        for(int i=0;i<=d1;i++){
            visited[x+i][y-i] = true;
        }

        for(int j=0;j<=d2;j++){
            visited[x + j][y + j] = true;
        }

        for(int i=0;i<=d2;i++){
            visited[x + d1 + i][y - d1 + i] = true;
        }

        for(int i=0;i<=d1;i++){
            visited[x + d2 + i][y + d2 - i] = true;
        }

        int min = Integer.MAX_VALUE;
        int max = -1;

        for(int i=0;i<x+d1;i++){
            for(int j=0;j<=y;j++){
                if(visited[i][j]){
                    break;
                }
                count1 += board[i][j];
                visited[i][j] = true;
            }
        }

        max = Math.max(max, count1);
        min = Math.min(min, count1);

        for(int i=0;i<=x+d2;i++){
            for(int j=N-1;j>y;j--){
                if(visited[i][j]){
                    break;
                }
                count2 += board[i][j];
                visited[i][j] = true;
            }
        }

        max = Math.max(max, count2);
        min = Math.min(min, count2);

        for(int i=x+d1;i<N;i++){
            for(int j=0;j<y-d1+d2;j++){
                if(visited[i][j]){
                    break;
                }
                count3 += board[i][j];
                visited[i][j] = true;
            }
        }

        max = Math.max(max, count3);
        min = Math.min(min, count3);

        for(int i=x+d2+1;i<N;i++){
            for(int j=N-1;j>=y-d1+d2;j--){
                if(visited[i][j]){
                    break;
                }
                count4 += board[i][j];
                visited[i][j] = true;
            }
        }

        max = Math.max(max, count4);
        min = Math.min(min, count4);


        count5 = total;
        count5 -= (count1 + count2 + count3 + count4);

        max = Math.max(max, count5);
        min = Math.min(min, count5);


        return max - min;

    }

}