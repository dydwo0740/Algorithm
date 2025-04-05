import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] items = new int[N + 1][2];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			items[i][0] = w;
			items[i][1] = v;
		}
		
		Arrays.sort(items, (o1, o2) -> o1[0] - o2[0]);
		
		int[][] dp = new int[N + 1][K + 1];
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=K;j++) {
				if(items[i][0] <= j) {
					dp[i][j] = Math.max(dp[i - 1][j], items[i][1] + dp[i - 1][j-items[i][0]]);
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		bw.write(dp[N][K] + "\n");
		bw.flush();
	}
}
