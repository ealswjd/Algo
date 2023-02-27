package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13904
public class Main_13904_과제 {
	static int N;
	static int[] days;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		PriorityQueue<Assignment> pq = new PriorityQueue<>();
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.offer(new Assignment(d, w));
		}//for
		br.close();
		
		days = new int[1004];
		
		System.out.print(work(pq));
	}//main
	
	private static int work(PriorityQueue<Assignment> pq) {
		int d, w, score = 0, day=0;
		
		Assignment assignment;		
		while(!pq.isEmpty()) {
			assignment = pq.poll();
			for(int i=assignment.d; i>=1; i--) {
				if(days[i]==0) {
					days[i] = assignment.w; 
					score+=days[i]; 
					break; 
				}
			}
		}//while
		
		return score;
	}//work

	static class Assignment implements Comparable<Assignment>{
		int d; // 과제 마감일까지 남은 일수
		int w; // 과제의 점수
		public Assignment(int d, int w) {
			super();
			this.d = d;
			this.w = w;
		}
		@Override
		public int compareTo(Assignment o) {
			return o.w - this.w;
		}
		@Override
		public String toString() {
			return "[d=" + d + ", w=" + w + "]";
		}
		
	}//Assignment

}//class
/*

7
4 60
4 40
1 20
2 50
3 30
4 10
6 5
->185

*/