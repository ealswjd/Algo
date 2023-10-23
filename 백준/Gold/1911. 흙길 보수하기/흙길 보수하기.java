import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 링크 : https://www.acmicpc.net/problem/1911
 * */
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
			pq.offer(new Position(start, end)); // 시작 위치 기준 오름차순 정렬
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
			cur = pq.poll(); // 현재 위치
			if(cur.end <= end) continue; // 끝 위치가 이전 끝 위치랑 겹치면 이미 널빤지 있는 상태
			else if(cur.start<=end) start = end+1; // 시작 위치가 이전 끝 위치보다 작으면 이전 끝 위치 다음부터 시작하기
			else if(cur.start > end) start = cur.start; // 시작 위치가 이전 끝 위치보다 크면 새로 시작.
			
			len = cur.end - start; // 웅덩이 길이
			if(len <= 0) continue;
			
			if(len <= L) cnt = 1; // 웅덩이 길이가 널빤지 길이보다 짧으면 한 개만 깔기
			else cnt = len%L==0 ? len/L : len/L+1; // 더 크다면 필요한 널빤지 개수 구하기
			
			sum += cnt; // 필요한 개수에 더해주기
			end = L*cnt + start - 1; // 끝 위치 갱신 (널빤지 개수 * 널빤지 길이 + 시작 위치 - 1)
		}//while
		
		return sum;
	}//getCnt

	static class Position implements Comparable<Position> {
		int start; // 시작 위치
		int end; // 끝 위치
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