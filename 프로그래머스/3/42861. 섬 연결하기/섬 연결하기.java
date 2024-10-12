import java.util.*;

class Solution {
    int[] parent;
    public int solution(int n, int[][] costs) {
        parent = new int[n];
        
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt((int[] o) -> o[2]));
        
        for(int i=0;i<costs.length;i++){
            int v1 = costs[i][0];
            int v2 = costs[i][1];
            int w = costs[i][2];
            
            pq.add(new int[]{v1, v2, w});
        }
        
        
        int answer = 0;
        
        while(!pq.isEmpty()){
            int[] con = pq.poll();
            
            if(union(con[0], con[1])){
                answer += con[2];
            }
        }
        
        return answer;
    }
    
    public int getParent(int x){
        if(parent[x] == x){
            return x;
        }
        return parent[x] = getParent(parent[x]);
    }
    
    public boolean union(int x, int y){
        x = getParent(x);
        y = getParent(y);
        
        if(x == y){
            return false;
        }
        
        if(x < y){
            parent[y] = x;
        } else{
            parent[x] = y;
        }
        
        return true;
    }
}