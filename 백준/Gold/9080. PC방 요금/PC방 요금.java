import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            String time = st.nextToken();
            String[] s = time.split(":");
            int start = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);

            int rest = Integer.parseInt(st.nextToken());

            if(rest <= 300){
                int ans = rest / 60;
                if(rest % 60 > 0){
                    ans++;
                }

                bw.write(ans * 1000 + "\n");
            } else{
                int ans = 0;
                while(rest > 0){
                    if(1320 <= start || start <= 480){
                        int can = start >= 1320 ? (1440 - start) + 480 : 480 - start;
                        int cnt = rest / 60;
                        if(rest % 60 > 0){
                            cnt++;
                        }
                        //System.out.println("cnt = " + cnt);
                        //System.out.println("can = " + can);
                        if(cnt <= 5){
                            ans += cnt * 1000;
                            rest -= cnt * 60;
                            start += cnt * 60;
                            start %= 1440;
                        } else{
                            if(can >= 300){
                                ans += 5000;
                                rest -= can;
                                start += can;
                                start %= 1440;
                            } else{
                                ans += 5000;
                                rest -= 300;
                                start += 300;
                                start %= 1440;
                            }
                        }
                    } else{
                        rest -= 60;
                        ans += 1000;
                        start += 60;
                        start %= 1440;
                    }

                    //System.out.println("start = " + start);
                    //System.out.println("rest = " + rest);
                }

                bw.write(ans+"\n");
            }
        }
        bw.flush();
    }
}