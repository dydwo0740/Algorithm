import java.io.*;
import java.util.*;

class Solution {
    public List<Integer> solution(String s) {
        s = s.substring(2, s.length() - 2);
        
        List<Integer> list = new ArrayList<>();
        
        
        String[] str = s.split("\\},\\{");

        
        Arrays.sort(str, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                return Integer.compare(o1.length(), o2.length());
            }
        });
        
        for(String string : str){
            String[] number = string.split(",");
            
            for(String num : number){
                int index = Integer.valueOf(num);
                
                if(!list.contains(index)){
                    list.add(index);
                }
            }
            
        }
        
        return list;
    }
}