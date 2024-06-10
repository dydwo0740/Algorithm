//package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Room {

	int start;
	int end;

	Room(int start, int end) {

		this.start = start;
		this.end = end;
	}
}

class Island {
	int from;
	int to;
	int weight;

	Island(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}

public class Main {

	public static int binary(ArrayList<Integer> list, int value, int left, int right) {
		while (left < right) {
			int mid = (left + right) / 2;

			if(list.get(mid)<value) {
				left = mid + 1;
			}
			else {
				right = mid;
			}
		}
		return left;
	}

	public static int binary_2(ArrayList<Integer> list, int value, int left, int right) {
		while (left < right) {
			int mid = (left + right) / 2;

			if (list.get(mid) >= value) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> LIS = new ArrayList<>();
		
		int N = Integer.parseInt(st.nextToken());
		
		int i;
		
		st = new StringTokenizer(br.readLine());
		
		for(i=0;i<N;i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		LIS.add(list.get(0));
		
		for(i=1;i<N;i++) {
			if(LIS.get(LIS.size() - 1)<list.get(i)) {
				LIS.add(list.get(i));
			}
			else {
				int idx = binary(LIS, list.get(i), 0, LIS.size());
				LIS.remove(idx);
				LIS.add(idx,list.get(i));
			}
		}
		
		bw.write(LIS.size()+"\n");
		bw.flush();
	}
}