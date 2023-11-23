import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static int M;
    static int[] row = {1,-1,0,0};
    static int[] col = {0,0,1,-1};
    static Map<Integer, Integer> map = new HashMap<>();
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        boolean[][] visited = new boolean[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!visited[i][j] && land[i][j] == 1){
                    bfs(visited, land, i, j);
                }
            }
        }
        int answer = Integer.MIN_VALUE;
        for(int i=0;i<M;i++){
            answer = Math.max(answer, map.getOrDefault(i, 0));
        }
        return answer;
    }
    
    public static void bfs(boolean[][] visited, int[][] land, int x, int y){
        int sum = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        
        Set<Integer> set = new HashSet<>();
        set.add(y);
    
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            sum++;
            
            for(int i=0;i<4;i++){
                int nx = cur[0] + row[i];
                int ny = cur[1] + col[i];
                
                if(0<= nx && nx < N && 0 <= ny && ny < M){
                    if(!visited[nx][ny] && land[nx][ny] == 1){
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                        set.add(ny);
                    }
                }
            }
        }
        
        Iterator iter = set.iterator();
        
        while(iter.hasNext()){
            int num = (int)iter.next();
            map.put(num, map.getOrDefault(num, 0) + sum);
        }
    }
}