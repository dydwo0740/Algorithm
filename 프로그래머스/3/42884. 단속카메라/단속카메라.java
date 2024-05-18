import java.io.*;
import java.util.*;

class Solution {
    static class Location{
        int left;
        int right;
        Location(int left, int right){
            this.left = left;
            this.right = right;
        }
    }
    public int solution(int[][] routes) {

        PriorityQueue<Location> pq = new PriorityQueue<>(new MyComparator());

        for(int i=0;i<routes.length;i++){
            pq.add(new Location(routes[i][0], routes[i][1]));
        }
        int ans = 0;
        Location location = pq.poll();
        int left = location.left;
        int right = location.right;
        ans++;
        while(!pq.isEmpty()){
            location = pq.poll();
            int start = location.left;
            int end = location.right;
            if(right < start){
                ans++;
                left = start;
                right = end;
            }
            else{
                if(right < end){
                    left = start;
                }
                else{
                    left = start;
                    right = end;
                }
            }
        }


        return ans;
    }

    public static class MyComparator implements Comparator<Location>{

        @Override
        public int compare(Location o1, Location o2){
            if(o1.left == o2.left){
                return o2.right - o1.right;
            }

            return o1.left - o2.left;
        }
    }
}