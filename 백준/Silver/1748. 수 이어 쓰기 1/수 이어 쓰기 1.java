import org.w3c.dom.Node;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int num = 0;

        int ans = 0;

        while ((int) Math.pow(10, num) <= N) {
            ans += ((num) * ((int) Math.pow(10, num) - (int) Math.pow(10, num - 1)));
            num++;
        }
        //bw.write(num + " " + ans + "\n");

        ans += (num) * (N - (int) Math.pow(10, num - 1) + 1);

        bw.write(ans + "\n");


        bw.flush();


    }
}