import java.util.*;

class Solution {
    static class Node{
        String name;
        int start; // 다 분으로 바꾸는게 좋습니다
        int playtime;
    }
    public String[] solution(String[][] plans) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.start));
        
        for(String[] plan : plans){
            Node node = new Node();
            node.name = plan[0];
            String[] s = plan[1].split(":");
            node.start = Integer.valueOf(s[0]) * 60 + Integer.valueOf(s[1]);
            node.playtime = Integer.valueOf(plan[2]);
            pq.add(node);
        }
        
        
        String[] answer = new String[plans.length];
        
        Stack<Node> stack = new Stack<>();
        
        int index=0;
        
        Node cur = pq.poll();
        int time = cur.start;
        
        while(!pq.isEmpty()){
            if(cur == null){ // 현재 할 수 있는게 없다.
                if(time == pq.peek().start){
                    cur = pq.poll();
                }
                else if(!stack.isEmpty()){
                    cur = stack.pop();
                }
            } else{ // 현재 내가 처리하는 아이가 있다
                if(time == pq.peek().start){
                    stack.push(cur);
                    cur = pq.poll();
                }
            }
            
            time++;
            
            if(cur == null){
                continue;
            }
            
            cur.playtime--;
            
            if(cur.playtime == 0){
                answer[index++] = cur.name;
                cur = null;
            }
        }
        
        if(cur != null){
            answer[index++] = cur.name;
        }
        
        while(!stack.isEmpty()){
            answer[index++] = stack.pop().name;
        }
        
        return answer;
    }
}