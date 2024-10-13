import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int el : scoville){
            pq.add(el);
        }
        
        int answer = 0;
        
        while(!pq.isEmpty()){
            int min = pq.poll();
            
            if(min >= K){
                return answer;
            }
            
            if(pq.isEmpty()){
                return -1;
            }
            
            int next = pq.poll();
            
            pq.add(min + next * 2);
            answer++;
        }
        
        return answer;
    }
}