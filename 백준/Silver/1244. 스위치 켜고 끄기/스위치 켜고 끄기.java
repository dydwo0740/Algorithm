import java.io.*;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] seq = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int gender = Integer.parseInt(st.nextToken());

            int num = Integer.parseInt(st.nextToken());

            if(gender == 1){
                changeWithM(seq, num);
            }else{
                changeWithW(seq, num);
            }

        }
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i=1;i<=N;i++){
            sb.append(seq[i] + " ");
            cnt++;

            if(cnt == 20){
                cnt = 0;
                sb.append("\n");
            }
        }

        bw.write(sb.toString() + "\n");
        bw.flush();

    }

    public static void changeWithM(int[] seq, int number) {
        int size = seq.length;

        for(int i=1;i<=size-1;i++){
            if(i % number == 0){
                seq[i] = (seq[i] == 0) ? 1 : 0;
            }
        }
    }

    public static void changeWithW(int[] seq, int mid){
        int left = mid;
        int right = mid;
        int size = seq.length;

        while(2 <= left && right <= size - 2){
            if(seq[left-1] == seq[right + 1]){
                left--;
                right++;
            }else{
                break;
            }
        }

        while(left <= right){
            seq[left] = (seq[left++] == 0) ? 1 : 0;
        }
    }


}