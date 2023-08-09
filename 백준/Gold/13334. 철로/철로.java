import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13334
public class Main {
	static int N, L, max;
	static PriorityQueue<Position> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 집
			int b = Integer.parseInt(st.nextToken()); // 사무실
			pq.offer(new Position(a, b));
		}//for
		L = Integer.parseInt(br.readLine()); // 철로 길이
		br.close();

		getMax();
		System.out.print(max);
	}//main
	
	private static void getMax() {
		max = 0; // 포함되는 사람들의 최대 수
		PriorityQueue<Integer> range = new PriorityQueue<>(); // 범위 해당 

		Position cur;
		while(!pq.isEmpty()) {
			cur = pq.poll(); // 현재 위치
			if(cur.len > L) continue; // 철로 길이 넘어가면 안됨 
			range.offer(cur.start); 
			
			while(!range.isEmpty() && range.peek() < cur.end - L) {
				range.poll(); // 범위 벗어나면 제거
			}//while
			
			max = Math.max(max, range.size()); // 최댓값 비교
		}//while
		
	}//getMax

	static class Position implements Comparable<Position> {
		int start; // 시작
		int end;   // 끝
		int len;   // 길이
		public Position(int a, int b) {
			this.start = Math.min(a, b);
			this.end = Math.max(a, b);
			len = this.end - this.start;
		}
		@Override
		public int compareTo(Position p) {
			if(this.end == p.end) return this.start - p.start;
			return this.end - p.end;
		}
	}//Position

}//class