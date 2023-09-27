import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2836
public class Main {
	static int N, M;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수상 택시를 타려고 하는 사람
		M = Integer.parseInt(st.nextToken()); // 최종 목적지
		pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			long start = Long.parseLong(st.nextToken()); // 탑승 위치
			long end = Long.parseLong(st.nextToken()); // 목적지
			if(start > end) pq.offer(new Node(start, end));
		}//for
		br.close();
		
		long cnt = getMoveCnt();
		System.out.print(cnt);
	}//main
	
	private static long getMoveCnt() {
		if(pq.isEmpty()) return M;
		
		long start=pq.peek().start, end=pq.poll().end;
		long sum = 0;
		Node cur;
		
		while(!pq.isEmpty()) {
			cur = pq.poll();
			if(cur.end <= start) start = Math.max(start, cur.start);
			else{
				sum += (start - end) * 2;
				start = cur.start;
				end = cur.end;
			}
		}//while
		
		sum += (start - end) * 2;
		return M + sum;
	}//getMoveCnt

	static class Node implements Comparable<Node> {
		long start;  // 탑승 위치
		long end; 	// 목적지
		public Node(long start, long end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Node node) {
			if(this.end == node.end) return (int) (this.start - node.start);
			return (int) (this.end - node.end);
		}	
	}//Node

}//class