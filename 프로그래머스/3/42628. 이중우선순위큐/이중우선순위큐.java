import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Comparator.reverseOrder());
        int size = 0;
        
        for(String str : operations){
            String[] s = str.split(" ");
            
            if(s[0].equals("I")){
                pq1.add(Integer.valueOf(s[1]));
                pq2.add(Integer.valueOf(s[1]));
                size++;
            } else{
                if(size == 0){
                    continue;
                }
                if(s[1].equals("1")){
                    int key = pq1.poll();
                    pq2.remove(key);
                } else{
                    int key = pq2.poll();
                    pq1.remove(key);
                }
                
                size--;
            }
        }
        
        
        if(size == 0){
            return new int[]{0,0};
        }
        
        
        
        return new int[]{pq1.peek(), pq2.peek()};
        
    }
}