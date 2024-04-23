import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // 우선 빌려줄 수 있는지 없는지를 체크하도록 하겠습니다. 
        
        List<Integer> list = new ArrayList<>();
        
        for(int i=0;i<reserve.length;i++){
            list.add(reserve[i]);
        }
        
        Arrays.sort(lost);
        
        int ans = n - lost.length;
        boolean[] visited = new boolean[lost.length];
        for(int i=0;i<lost.length;i++){
            if(list.contains(lost[i])){
                visited[i] = true;
                list.remove(list.indexOf(lost[i]));
                ans++;
            }
        }
        //System.out.println(ans);
        for(int i=0;i<lost.length;i++){
            if(visited[i]){
                continue;
            }
            
            if(list.contains(lost[i] - 1)){
                list.remove(list.indexOf(lost[i] - 1));
                ans++;
                continue;
            }
            
            if(list.contains(lost[i] + 1)){
                list.remove(list.indexOf(lost[i] + 1));
                ans++;
                continue;
            }
        }
        
        return ans;
    }
}