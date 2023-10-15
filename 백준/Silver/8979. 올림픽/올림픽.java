import org.w3c.dom.Node;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static class Info{
        int num;
        int gold;
        int silver;
        int bronze;

        Info(int num, int gold, int silver, int bronze) {
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(st.nextToken());

        List<Info> comp = new ArrayList<>();

        int g = -1;
        int s = -1;
        int b = -1;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());

            int gold = Integer.parseInt(st.nextToken());

            int silver = Integer.parseInt(st.nextToken());

            int bronze = Integer.parseInt(st.nextToken());

            if(num == K){
                g = gold;
                s = silver;
                b = bronze;
            }else{
                comp.add(new Info(num, gold, silver, bronze));
            }
        }

        int rank = 0;

        for (Info info : comp) {
            if (info.gold > g) {
                rank++;
            } else if (info.gold == g) {
                if (info.silver > s) {
                    rank++;
                } else if (info.silver == s) {
                    if (info.bronze > b) {
                        rank++;
                    } else if (info.bronze == b) {

                    }else{

                    }
                }else{

                }
            }
        }


        bw.write((rank+1)+"\n");
        bw.flush();


    }
}