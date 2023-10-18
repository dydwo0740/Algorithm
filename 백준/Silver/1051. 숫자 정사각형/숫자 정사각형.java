import java.io.*;
import java.util.*;

public class Main {

    static int[] row = {1, 1, 0};
    static int[] col = {0, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        int[][] seq = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            for(int j=0;j<M;j++){
                seq[i][j] = (int)(str.charAt(j) - '0');
            }
        }

        int answer = 1;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                answer = Math.max(answer, (int)Math.pow(initialize(seq, i, j), 2));
            }
        }


        bw.write(answer+"\n");

        bw.flush();
    }

    public static int initialize(int[][] seq, int x, int y){
        int N = seq.length;
        int M = seq[0].length;

        int len = 1;
        int answer = 0;

        while(true){

            boolean flag = false;
            boolean check = false;

            for(int i=0;i<3;i++){
                int nx = x + row[i] * len;
                int ny = y + col[i] * len;

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(seq[x][y] != seq[nx][ny]){
                        check = true;
                    }
                }else{
                    flag = true;
                    break;
                }
            }

            if(flag){
                break;
            }

            if(!check) {
                answer = Math.max(answer, len);
            }
            len++;
        }

        return answer + 1;
    }

}