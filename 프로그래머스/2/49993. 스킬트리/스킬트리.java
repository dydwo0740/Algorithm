import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(String comp : skill_trees){
            if(isPossible(skill, comp)){
                answer++;
            }
        }
        return answer;
    }
    
    public boolean isPossible(String skill, String comp){
        int prev = comp.indexOf(skill.charAt(0));
        
        for(int i=1;i<skill.length();i++){
            int cur = comp.indexOf(skill.charAt(i));
            
            if(cur == -1){
                prev = -1;
                continue;
            }
            
            if(prev == -1){
                return false;
            }
            
            if(prev > cur){
                return false;
            }
            
            prev = cur;
        }
        
        return true;
    }
}