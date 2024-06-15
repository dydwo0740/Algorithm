//package Algorithm;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

class Location {

	int x;
	int y;

	Location(int x, int y) {

		this.x = x;
		this.y = y;
	}
}

class Line {

	int idx;
	int value;

	Line(int w, int v) {

		this.idx = w;
		this.value = v;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(st.nextToken());
		
		int[][] seq = new int[N+1][N+1];
		int[][] dp = new int[N+1][N+1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				seq[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				dp[i][j] = seq[i][j] + dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1];
				//bw.write(dp[i][j] + " ");
			}
			//bw.write("\n");
		}
		
		while(M>0) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			if(x1==x2&&y1==y2) {
				bw.write(seq[x1][y1]+"\n");
			}
			else {
				int sum = dp[x2][y2];
				
				//bw.write(sum+"\n");
				
				sum -= dp[x1-1][y2];
				
				//bw.write(sum+"\n");
				
				sum -= dp[x2][y1 - 1];
				//bw.write(sum+"\n");
			    sum += dp[x1-1][y1-1];
			    //bw.write(sum+"\n");
				
				
				bw.write(sum+"\n");
			}
			
			M--;
		}
		
		bw.flush();
	}

}
