import java.io.*;
import java.util.*;
class Solution {
    static int[] output;
    static int answer = -1;
    public int solution(int k, int[][] dungeons) {
        int size = dungeons.length;
        output = new int[size];
        boolean[] visited = new boolean[size];
        dfs(dungeons, visited, 0, size, k);
        
        return answer;
    }
    
    public void dfs(int[][] dungeons, boolean[] visited, int depth, int size, int k){
        if(depth == size){
            initialize(dungeons, k, size);
            return;
        }
        
        for(int i=0;i<size;i++){
            if(!visited[i]){
                visited[i] = true;
                output[depth] = i;
                dfs(dungeons, visited, depth + 1, size, k);
                visited[i] = false;
            }
        }
    }
    
    public void initialize(int[][] dungeons, int k, int size){
        int cnt = 0;
        for(int i=0;i<size;i++){
            int idx = output[i];
            if(dungeons[idx][0] <= k){
                k -= dungeons[idx][1];
                cnt++;
            }else{
                break;
            }
        }
        
        answer = Math.max(answer, cnt);
    }
}