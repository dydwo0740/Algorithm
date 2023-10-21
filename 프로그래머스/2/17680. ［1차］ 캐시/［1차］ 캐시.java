import java.io.*;
import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        Queue<String> queue = new LinkedList<>();
        int answer = 0;
        
        for(String city : cities){
            
            city = city.toUpperCase();
            
            
            if(queue.contains(city)){
                answer += 1;
                queue.remove(city);
                queue.add(city);
            } else{
                answer += 5;
                
                if(queue.size() >= cacheSize){
                    queue.poll();
                }
                
                if(queue.size() < cacheSize){
                    queue.add(city);
                }
            }
        }
        
        return answer;
    }
}