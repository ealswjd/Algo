import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1966
public class Main {
	static int N, M;
	static PriorityQueue<Document> pq;
	static Queue<Document> printer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());		
		
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 문서의 개수
			M = Integer.parseInt(st.nextToken()); // 순서가 궁금한 문서
			
			pq = new PriorityQueue<>();
			printer = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for(int idx=0; idx<N; idx++) {
				int num = Integer.parseInt(st.nextToken());
				pq.offer(new Document(idx, num));
				printer.offer(new Document(idx, num));
			}//for
			
			ans.append(getOrder()).append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main
	
	private static int getOrder() {
		int order = 1;
		
		Document cur;
		while(!printer.isEmpty()) {
			cur = printer.poll();
			
			if(pq.peek().num > cur.num) printer.offer(cur);				
			else {
				if(cur.idx == M) return order;
				pq.poll();
				order++;
			}//else
			
		}//while
		
		return order;
	}//getOrder

	static class Document implements Comparable<Document> {
		int idx;
		int num;
		public Document(int idx, int num) {
			this.idx = idx;
			this.num = num;
		}
		@Override
		public int compareTo(Document d) {
			return d.num - this.num;
		}
	}//Document

}//class