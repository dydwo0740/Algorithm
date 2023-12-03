import java.io.*;
import java.util.*;
class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for(int i=0;i<numbers.length;i++){
            if(numbers[i] % 2 == 0){
                answer[i] = numbers[i] + 1;
            } else{
                String s = Long.toString(numbers[i], 2);
                int zeroIdx = s.lastIndexOf("0");
                if(zeroIdx != -1){
                    s = s.substring(0, zeroIdx) + "10" + s.substring(zeroIdx + 2, s.length());
                    answer[i] = Long.parseLong(s,2);
                } else{
                    s = "10" + s.substring(1,s.length());
                    answer[i] = Long.parseLong(s,2);
                }
            }
        }
        
        return answer;
    }
}