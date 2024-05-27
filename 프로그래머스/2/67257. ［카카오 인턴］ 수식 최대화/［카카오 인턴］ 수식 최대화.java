import java.util.*;

class Solution {
    static String[][] op = {{"+", "-", "*"}, {"+", "*", "-"}, {"-", "+", "*"}, {"-", "*", "+"}, {"*", "+", "-"}, {"*", "-", "+"}};
    public long solution(String expression) {
        long answer = 0;
        List<String> origin = new ArrayList<>();
        int start = 0;
        
        for(int i=0;i<expression.length();i++){
            if(expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*'){
                origin.add(expression.substring(start, i));
                origin.add(expression.charAt(i) + "");
                start = i + 1;
            }
        }
        
        origin.add(expression.substring(start, expression.length()));
        
        
        for(int i=0;i<6;i++){
            List<String> temp = new ArrayList<>();
            temp.addAll(origin);
            for(int j=0;j<3;j++){
                for(int k=0;k<temp.size();k++){
                    if(temp.get(k).equals(op[i][j])){
                        //System.out.println(op[i][j] + " " + temp.get(k-1) + " " + temp.get(k+1));
                        Long left = Long.parseLong(temp.get(k-1));
                        Long right = Long.parseLong(temp.get(k+1));
                        temp.set(k-1, String.valueOf(calc(left, right, op[i][j])));
                        temp.remove(k);
                        temp.remove(k);
                        k--;
                    }
                }
            }
            
            answer = Math.max(answer, Math.abs(Long.parseLong(temp.get(0))));
        }
        
        
        return answer;
    }
    
    public Long calc(Long left, Long right, String op){
        if(op.equals("+")){
            return left + right;
        } else if(op.equals("-")){
            return left - right;
        } 
        
        return left * right;
    }
}