import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> total = new HashMap<>();
        Map<Integer, Integer> state = new HashMap<>();
        
        for(String record : records){
            String[] s = record.split(" ");
            String[] times = s[0].split(":");
            
            int time = Integer.valueOf(times[0]) * 60 + Integer.valueOf(times[1]);
            int number = Integer.valueOf(s[1]);
            String type = s[2];
            
            if(type.equals("OUT")){
                int before = state.get(number);
                int diff = time - before;
                
                state.remove(number); // 삭제 해볼까요?
                
                total.put(number, total.getOrDefault(number, 0) + diff);
            } else{
                state.put(number, time);
            }
        }
        
        for(Map.Entry<Integer, Integer> entry : state.entrySet()){
            int before = entry.getValue();
            int time = 23 * 60 + 59;
            int diff = time -  before;
            
            total.put(entry.getKey(), total.getOrDefault(entry.getKey(), 0) + diff);
        }
        
        int[] answer = new int[total.size()];
        List<int[]> list = new ArrayList<>();
        
        for(Map.Entry<Integer, Integer> entry : total.entrySet()){
            int rest = entry.getValue() - fees[0];
            //System.out.println((int) Math.ceil((double) rest / fees[2]));
            if(rest <= 0){
                list.add(new int[]{entry.getKey(), fees[1]});
                continue;
            }
            
            list.add(new int[]{entry.getKey(), fees[1] + (int) Math.ceil((double) rest / fees[2]) * fees[3]});
        }
        
        list.sort(Comparator.comparingInt((int[] o) -> o[0]));
    
        for(int i=0;i<list.size();i++){
            answer[i] = list.get(i)[1];
        }
        
        return answer;
    }
}