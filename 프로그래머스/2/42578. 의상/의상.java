import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        
        Map<String, Integer> map = new HashMap<>();
        
        for(int i=0;i<clothes.length;i++){
            String key = clothes[i][1];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            answer *= (entry.getValue() + 1);
        }
        
        
        return answer - 1;
    }
}