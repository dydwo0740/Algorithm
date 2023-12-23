import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int[][] map;
    static int N;
    static int[] dx = {0, 1, -1, 0, 1, 1, -1, -1};
    static int[] dy = {1, 0, 0, -1, 1, -1, 1, -1};    //상하좌우,대각선 8방향

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<N; j++) {
                map[i][j] = input.charAt(j)- '0';
            }
        }

        if(N<3) {             //N<3인경우 무조건 0
            System.out.println(0);
            return;
        }

        int ans = sol();

        System.out.println(ans);
    }

    public static int sol() {
        int cnt = 0;

        for(int i=1; i<N-1; i++) {
            loop:for(int j=1; j<N-1; j++) {
                for(int k=0; k<8; k++) {    //8방향 체크
                    int nx = i+dx[k];
                    int ny = j+dy[k];

                    if(map[nx][ny]>3) continue;   //닫힌 곳이면 그냥 넘어감

                    if(map[nx][ny]==0) continue loop;   //지뢰가 있을 수 없는 곳
                }

                for(int k=0; k<8; k++) {      //지뢰가 있을 수 있는 곳이면 테두리 숫자 하나씩 빼줌
                    int nx = i+dx[k];
                    int ny = j+dy[k];

                    if(map[nx][ny]<=3)
                        map[nx][ny]--;
                }

                cnt++;      //지뢰 하나씩 증가
            }
        }

        return cnt;
    }
}