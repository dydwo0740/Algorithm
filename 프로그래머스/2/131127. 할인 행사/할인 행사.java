import java.io.*;
import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        for(int i=0;i<discount.length;i++){
            Map<String, Integer> map = new HashMap<>();
            if(i + 10 > discount.length){
                break;
            }
            for(int j=i;j<i + 10;j++){
                map.put(discount[j], map.getOrDefault(discount[j], 0) + 1);
            }
            
            boolean flag = true;
            for(int j=0;j<want.length;j++){
                if(number[j] <= map.getOrDefault(want[j], 0)){
                    continue;
                }
                flag = false;
                break;
            }
            
            if(flag){
                answer++;
            }
            
            
        }
        return answer;
    }
}