package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_8980_택배 {
	static int N, C, M;
	static int[] counts;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = parseInt(st.nextToken()); // 마을 수 N
		C = parseInt(st.nextToken()); // 트럭의 용량 C
		M = parseInt(br.readLine()); // 보내는 박스 정보의 개수 M
		
		counts = new int[N+1];
		Arrays.fill(counts, C);
		counts[0] = 0;
		
		PriorityQueue<Info> pq = new PriorityQueue<>();
		// 박스를 보내는 마을번호, 박스를 받는 마을번호, 보내는 박스 개수(1이상 10,000이하 정수)
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = parseInt(st.nextToken());
			int to = parseInt(st.nextToken());
			int cnt = parseInt(st.nextToken());
			pq.offer(new Info(from, to, cnt));
		}//for
		br.close();		

		System.out.print( delivery(pq) );
	}//main

	private static int delivery(PriorityQueue<Info> pq) {
		int sum = 0, min;
		int from, to=0, cnt;

		Info info;
		while(!pq.isEmpty()) {
			info = pq.poll();
			from = info.from;
			to = info.to;
			cnt = info.cnt;
			if(counts[from] >= 0) {
				for(int i=from; i<to; i++) {
					cnt = Math.min(counts[i], cnt);
				}			
				for(int i=from; i<to; i++) counts[i] -= cnt;
				sum += cnt;
			}
			
			counts[to] += counts[to]==C ? 0 : cnt;
		}//while
		
		return sum;
	}//delivery

	private static int parseInt(String s) {
		return Integer.parseInt(s);		
	}//parseInt
	
	static class Info implements Comparable<Info>{
		int from; // 박스를 보내는 마을번호
		int to; // 박스를 받는 마을번호
		int cnt; // 보내는 박스 개수
		public Info(int from, int to, int cnt) {
			super();
			this.from = from;
			this.to = to;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Info o) {
			if(this.to == o.to) {
				return this.from - o.from;
			}
			return this.to - o.to;
		}		
	}//Info

}//class


/*

4 40
6
3 4 20
1 2 10
1 3 20
1 4 30
2 3 10
2 4 20

*/
