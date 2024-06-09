import java.util.*;
class Solution {
    public String solution(String m, String[] musicinfos) {
        
        m = m.replaceAll("C#", "H");
        m = m.replaceAll("D#", "I");
        m = m.replaceAll("F#", "J");
        m = m.replaceAll("G#", "K");
        m = m.replaceAll("A#", "L");
        m = m.replaceAll("B#", "M");
        
        String answer = "";
        int answerT = -1;
        for(int i=0;i<musicinfos.length;i++){
            String[] infos = musicinfos[i].split(",");
            
            String[] startTime = infos[0].split(":");
            String[] endTime = infos[1].split(":");
            String title = infos[2];
            String music = infos[3];
            
            music = music.replaceAll("C#", "H");
            music = music.replaceAll("D#", "I");
            music = music.replaceAll("F#", "J");
            music = music.replaceAll("G#", "K");
            music = music.replaceAll("A#", "L");
            music = music.replaceAll("B#", "M");
            
            int start = Integer.valueOf(startTime[0]) * 60 + Integer.valueOf(startTime[1]);
            int end = Integer.valueOf(endTime[0]) * 60 + Integer.valueOf(endTime[1]);
            
            int time = end - start;
            
            String str = "";
            int index = 0;
            
            while(index < time){
                str += String.valueOf(music.charAt(index % music.length()));
                index++;
            }
            
            if(str.contains(m)){
                if(answerT < time){
                    answerT = time;
                    answer = title;
                }
            }
                                
        }
        return answerT == -1 ? "(None)" : answer;
    }
}