import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=1;i<brown;i++){
            // i 는 세로
            if((brown - (2 * i)) % 2== 0){
                int j = (brown - (2 * i)) / 2 + 2;
                if(i * j == brown + yellow){
                    max = Math.max(i,j);
                    min = Math.min(i,j);
                }
            }
        }
        
        
        return new int[]{max, min};
    }
}