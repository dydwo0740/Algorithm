import java.util.*;
class Solution {
    static int[][] board;
    static int N;
    static int M;
    static int[] row = {0, 0, 1, 1};
    static int[] col = {0, 1, 0, 1};
    static int answer;
    public int solution(int m, int n, String[] origin) {
        N = m;
        M = n; // N이 세로 그리고 M이 가로입니다.
        answer = 0;
        board = new int[N][M];
        
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                char ch = origin[i].charAt(j);
                board[i][j] = ch - 'A' + 1; // 1부터가 A 그리고 0은 empty를 의미합니다.
            }
        }
        
        while(isMoved()){
        }
        
        
        return answer;
    }
    
    public static boolean isMoved(){
        // set으로 처리를 합시다.
        Set<Integer> set = new HashSet<>();
        
        for(int x=0;x<N;x++){
            for(int y=0;y<M;y++){
                int std = board[x][y];
                if(std == 0){
                    continue;
                }
                boolean flag = true;
                for(int i=0;i<4;i++){
                    int nx = x + row[i];
                    int ny = y + col[i];
                    
                    if(0 <= nx && nx < N && 0 <= ny && ny < M && board[nx][ny] == std){
                        continue;
                    }
                    flag = false;
                }
                
                if(flag){
                    for(int i=0;i<4;i++){
                        int nx = x + row[i];
                        int ny = y + col[i];
                        set.add(nx * M + ny);
                    }
                }
            }
        }
        
        if(set.isEmpty()){
            return false;
        }
        
        Iterator<Integer> iter = set.iterator();
        while(iter.hasNext()){
            int index = iter.next();
            board[index / M][index % M] = 0;
            answer++;
        }
        
        for(int i=N-1;i>0;i--){
            for(int j=0;j<M;j++){
                if(board[i][j] == 0){
                    for(int k=i-1;k>=0;k--){
                        if(board[k][j] != 0){
                            board[i][j] = board[k][j];
                            board[k][j] = 0;
                            break;
                        }
                    }
                }
            }
        }
        
        return true;
    }
}