import java.util.*;
class Solution {
    static int ban;
    static int[] inorder;
    static int[] outorder;
    static List<Integer>[] list;
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        inorder = new int[1000001];
        outorder = new int[1000001];
        list = new ArrayList[1000001];
        boolean[] visited = new boolean[1000001];
        
        int num = 0;
        for(int i=1;i<=1000000;i++){
            list[i] = new ArrayList<>();
        }
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            
            list[from].add(to);
            outorder[from]++;
            inorder[to]++;
        }
        
        for(int i=1;i<=1000000;i++){
            if(outorder[i] > 1 && inorder[i] == 0){
                num = outorder[i];
                answer[0] = i;
                break;
            }
        }
        
        outorder = new int[1000001];
        inorder = new int[1000001];
        
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            
            if(from == answer[0]){
                visited[to] = true;
                continue;
            }
            visited[from] = true;
            visited[to] = true;
            list[from].add(to);
            outorder[from]++;
            inorder[to]++;
        }
        
        //System.out.println(inorder[2] + " " + visited[2]);
    
        for(int i=1;i<=1000000;i++){
            if(answer[0] == i){
                continue;
            }
            
            if(outorder[i] == 0 && visited[i]){
                //System.out.println(i + " " + outorder[i] + " " + inorder[i]);
                answer[2]++;
            } else if(outorder[i] == 2 && inorder[i] == 2){
                answer[3]++;
            }
        }
        
        answer[1] = num - answer[2] - answer[3];
        
        return answer;
    }
}