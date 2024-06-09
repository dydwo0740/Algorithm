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

class Bag {

	int w;
	int v;

	Bag(int w, int v) {

		this.w = w;
		this.v = v;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String str1 = st.nextToken();

		st = new StringTokenizer(br.readLine());

		String str2 = st.nextToken();

		int[][] dp = new int[str1.length() + 1][str2.length() + 1];// 문자열의 길이 만큼 배열크기를 늘립니다.

		for(int i=1;i<=str1.length();i++) {
			for(int j=1;j<=str2.length();j++) {
				if(str1.charAt(i - 1)==str2.charAt(j - 1)) {
					dp[i][j] = dp[i-1][j - 1] + 1;//아직 추가안된 것에 추가함으로 바뀜
				}
				
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
	    bw.write(dp[str1.length()][str2.length()]+"\n");
	    bw.flush();

	}
}
