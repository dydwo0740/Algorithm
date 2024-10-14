import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        Set<String> set = new HashSet<>();
        
        for(String number : phone_book){
            set.add(number);
        }
        
        for(String number : phone_book){
            for(int i=0;i<number.length() - 1;i++){
                String key = number.substring(0, i + 1);
                if(set.contains(key)){
                    return false;
                }
            }
        }
        
        
        return true;
    }
}