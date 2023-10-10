import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        
        int N = elements.length;
        
        for(int size = 1; size <= N;size++){
            
            for(int i=0;i<elements.length;i++){
                int sum = 0;
                for(int j=0;j<size;j++){
                    sum += elements[(j + i) % N];
                }
                set.add(sum);
            }
        }
        
        return set.size();
    }
}