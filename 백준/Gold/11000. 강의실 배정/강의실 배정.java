//package hello;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Lecture {
	int start;
	int end;

	Lecture(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Lecture> list = new ArrayList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		int N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.add(new Lecture(start, end));
		}
		

		Comparator<Lecture> nc = new Comparator<Lecture>() {

			@Override
			public int compare(Lecture o1, Lecture o2) {
				// TODO Auto-generated method stub
				if (o1.start == o2.start) {
					return o1.end - o2.end;
				}
				return o1.start - o2.start;
			}
		};

		Collections.sort(list, nc);

		pq.add(list.get(0).end);
		
		for (int i = 1; i < N; i++) {
			int start = list.get(i).start;
			int end = list.get(i).end;
			
			if(pq.peek()<=start) {
				pq.poll();
				pq.add(end);
			}
			else {
				pq.add(end);
			}

		}
		
		bw.write(pq.size()+"\n");
		bw.flush();

	}

	public static void getSubsequence(int idx, int end, int sum, ArrayList<Integer> list, ArrayList<Integer> ans) {
		if (idx == end) {
			ans.add(sum);
			return;
		}
		getSubsequence(idx + 1, end, sum + list.get(idx), list, ans);
		getSubsequence(idx + 1, end, sum, list, ans);
	}

}