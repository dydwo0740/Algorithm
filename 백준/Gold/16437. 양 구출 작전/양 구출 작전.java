import java.util.*;
import java.io.*;

class Main {
    //섬에 정보 관련 클래스
    static class info{
        long value;	//마릿수
        boolean kinds;	//true : 양, false : 늑대
        public info(long value, boolean kinds) {
            this.kinds = kinds;
            this.value = value;
        }
    }
    //트리 정점 연결하는 간선 정보
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static info[] infos;	//각 섬에 정보 저장 배열
    public static void main(String args[]) throws IOException{
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        infos = new info[N+1];
        for(int i=0;i<=N;i++)
            tree.add(new ArrayList<>());
        StringTokenizer st;
        //각 섬의 정보 및 트리 간선 수립!
        infos[1] = new info(0, true);
        for(int i=2;i<=N;i++) {
            st = new StringTokenizer(br.readLine()," ");
            char t = st.nextToken().charAt(0);
            long a = Long.parseLong(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            tree.get(p).add(i);
            infos[i] = new info(a, t == 'S');
        }
        //후위탐색으로 얻은 1번섬의 양의 마릿수 BufferedWriter 저장
        bw.write(search(1) + "");
        bw.flush();
        bw.close();
        br.close();
    }
    //후위 탐색(DFS)으로 각 섬에서 양들이 1번섬으로 오는 과정 탐색
    static long search(int index) {
        long sum = 0;	//현재 양의 마릿수
        //Left, Right 정점 탐색!
        for(int next : tree.get(index)){
            sum += search(next);
        }
        //Root 정점 탐색
        if(infos[index].kinds)	//현재 섬이 양일 때
            return sum + infos[index].value;	//양의 마릿수 그대로 이동!
        else	//현재 섬이 늑대일 때
            return (sum - infos[index].value < 0) ? 0 : sum-infos[index].value;
            //늑대가 현재까지 이동한 양보다 많으면 양은 0마리 이동!
            //늑대가 현재까지 이동한 양보다 적으면 (양 - 늑대)마리 이동! 
    }
}