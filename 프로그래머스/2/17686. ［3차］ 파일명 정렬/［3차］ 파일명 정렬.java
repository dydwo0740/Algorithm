import java.util.*;
class Solution {
    static class File implements Comparable<File>{
        private String head;
        private int num;
        private String tail;
        private String all;
        private int index; 
        public File(String head, int num,String tail,String all,int index){
            this.head = head;
            this.num = num;
            this.tail= tail;
            this.all = all;
            this.index = index;
        }
        public int compareTo(File o){
            if(head.equals(o.head) && num == o.num){
                return index-o.index;
            }else if( head.equals(o.head) ){
                return num-o.num;
            }else{
                return head.compareTo(o.head);
            }
        }
    }
    static List<File> list = new ArrayList<>();
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        for(int i = 0 ; i < files.length;i++){
        
            int a = 0;
            int b = 0;
            for(int j = 0 ; j < files[i].length();j++){
                  if(Character.isDigit(files[i].charAt(j))){
                      a=j;
                      break;
                  }  
            }
            for(int j = 0; a + j <files[i].length() && j < 5;j++){
                 if(!Character.isDigit(files[i].charAt(a+j))){
                      b = a+j;
                      break;
                  } 
                
            }
            //System.out.println(a+ " "+b);
            if(b == 0){
                if(a + 5 < files[i].length()){
                    b = a + 5;
                } else{
                    b = files[i].length();
                }
            }
            
            String h = files[i].substring(0,a).toUpperCase();
            int n = Integer.parseInt(files[i].substring(a,b));
            String t = "";
            if(b != files[i].length()){
                t = files[i].substring(b,files[i].length());
            }
            
            list.add(new File(h,n,t,files[i],i));
        }
        
        //
        Collections.sort(list);
        for(int i = 0 ; i < list.size();i++){
            answer[i] = list.get(i).all;
        }
        return answer;
    }
}