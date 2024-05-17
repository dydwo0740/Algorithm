import java.util.*;
class Solution {
    static class Node{
        int index;
        int value;
        int pk;
        
        Node(int index, int value, int pk){
            this.index = index;
            this.value = value;
            this.pk = pk;
        }
    }
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        List<Node> nodes = new ArrayList<>();
        for(int i=0;i<data.length;i++){
            nodes.add(new Node(i, data[i][col-1], data[i][0]));
        }
        
        nodes.sort(Comparator.comparingInt((Node o) -> o.value).thenComparing(Comparator.comparingInt((Node o) -> o.pk).reversed()));
        
        List<Integer> list = new ArrayList<>();
        
        for(int i=row_begin - 1;i<=row_end - 1;i++){
            int idx = nodes.get(i).index;
            int sum = 0;
            for(int j=0;j<data[idx].length;j++){
                sum += (data[idx][j] % (i + 1));
            }
            
            list.add(sum);
        }
        
        for(int num : list){
            answer = answer ^ num;
        }
        
        return answer;
    }
}