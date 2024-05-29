import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        for(int i=0;i<s.length();i++){
            String comp = s.substring(i, s.length()) + s.substring(0, i);
            if(isGood(comp)){
                //System.out.println(comp);
                answer++;
            }
        }
        return answer;
    }
    
    public boolean isGood(String s){
        Stack<Character> stack = new Stack<>();
        
        for(int i=0;i<s.length();i++){
            if(stack.isEmpty()){
                stack.push(s.charAt(i));
            } else{
                if(s.charAt(i) == ']' || s.charAt(i) == '}' || s.charAt(i) == ')'){
                    if(s.charAt(i) == ']' && stack.peek() == '['){
                        stack.pop();
                    } else if(s.charAt(i) == '}' && stack.peek() == '{'){
                        stack.pop();
                    } else if(s.charAt(i) == ')' && stack.peek() == '('){
                        stack.pop();
                    }
                } else{
                    stack.push(s.charAt(i));
                }
            }
        }
        
        if(stack.isEmpty()){
            return true;
        }
        
        return false;
    }
}