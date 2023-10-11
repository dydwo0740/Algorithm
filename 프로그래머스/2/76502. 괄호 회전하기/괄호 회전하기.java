import java.io.*;
import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        
        int N = s.length() - 1;
        
        if(N == 1){
            return 0;
        }
        
        for(int i=0;i<N;i++){
            if(check(s)){
                answer++;
            }
            s = s.substring(1, s.length()) + s.substring(0,1);
        }
        return answer;
        
    }
    
    public boolean check(String s){
        Stack<Character> stack = new Stack<>();
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) =='('){
                stack.push('(');
            }else if(s.charAt(i) == '{'){
                stack.push('{');
            }else if(s.charAt(i) == '['){
                stack.push('[');
            }else if(s.charAt(i) == ')'){
                if(stack.isEmpty() || stack.pop() != '('){
                    return false;
                }
            }else if(s.charAt(i) == '}'){
                if(stack.isEmpty() || stack.pop() != '{'){
                    return false;
                }
            }else if(s.charAt(i) == ']'){
                if(stack.isEmpty() || stack.pop() != '['){
                    return false;
                }
            }
        }
        
        if(!stack.isEmpty()){
                System.out.println(stack.size());
                return false;
            }
        
        return true;
    }
}