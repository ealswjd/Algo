import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1911
public class Main {
	static int N, L;
	static PriorityQueue<Position> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 물 웅덩이 개수
		L = Integer.parseInt(st.nextToken()); // 널빤지 길이
		
		pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()); // 웅덩이 시작 위치
			int end = Integer.parseInt(st.nextToken()); // 웅덩이 끝 위치
			pq.offer(new Position(start, end));
		}//for
		br.close();
		
		int cnt = getCnt();
		System.out.print(cnt);
	}//main
	
	private static int getCnt() {
		int start=pq.peek().start, end=-1;
		int sum = 0, cnt = 0, len = 0;
		
		Position cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			if(cur.end <= end) continue;
			else if(cur.start<=end) start = end+1;
			else if(cur.start > end) start = cur.start;
			
			len = cur.end - start;
			if(len <= 0) continue;
			
			if(len <= L) cnt = 1;
			else cnt = len%L==0 ? len/L : len/L+1;
			
			sum += cnt;
			end = L*cnt + start - 1;
		}//while
		
		return sum;
	}//getCnt

	static class Position implements Comparable<Position> {
		int start;
		int end;
		public Position(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Position p) {
			return this.start - p.start;
		}	
	}//Position

}//class