import java.io.*;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[101][101];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());

            int y = Integer.parseInt(st.nextToken());

            for(int row = x; row <= x + 9; row++){
                for(int col = y; col <= y + 9; col++){
                    visited[row][col] = true;
                }
            }
        }

        int ans = 0;

        for(int i=1;i<=100;i++){
            for(int j=1;j<=100;j++){
                if (visited[i][j]) {
                    ans++;
                }
            }
        }
        bw.write(ans+"\n");
        bw.flush();

    }


}