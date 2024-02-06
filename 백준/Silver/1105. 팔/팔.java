import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String L = st.nextToken();
		String R = st.nextToken();
		
		int index = 0;
		int cnt = 0;
		
		if(L.length() < R.length()) {
			bw.write(0 + "\n");
			bw.flush();
			return;
		}
		
		for(int i=0;i<L.length();i++) {
			if(L.charAt(i) < R.charAt(i)) {
				break;
			} else if(L.charAt(i) == '8' && R.charAt(i) == '8') {
				cnt++;
			}
		}
		
		
		bw.write(cnt + "\n");
		bw.flush();
	}
}
