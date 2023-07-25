import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1647
public class Main {
	static int N, M, min;
	static int[] parent;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N개의 집
		M = Integer.parseInt(st.nextToken()); // 집들을 연결하는 M개의 길		
		
		init();
		
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.offer(new Node(a, b, c));
		}//while
		br.close();
		
		getMin();
		System.out.print(min);

	}//main


	private static void getMin() {
		int cnt = 0;
		Node cur;
		
		while(!pq.isEmpty()) {
			cur = pq.poll();
			if(cnt == N-2) break;
			if(find(cur.from) != find(cur.to)) {
				union(cur.from, cur.to);
				min += cur.cost;
				cnt++;
			}
		}//while
		
	}//getMin


	private static void union(int from, int to) {
		from = find(from);
		to = find(to);
		
		if(from < to) parent[to] = from;
		else parent[from] = to;		
	}//union


	private static int find(int from) {
		if(parent[from] == from) return from;
		return parent[from] = find(parent[from]);
	}//find


	private static void init() {
		min = 0;
		pq = new PriorityQueue<>();
		parent = new int[N+1];
		for(int i=0; i<=N; i++) {
			parent[i] = i;
		}//for
		
	}//init


	static class Node implements Comparable<Node> {
		int from;
		int to; // 연결 집
		int cost; // 길의 유지비
		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node n) {
			return this.cost - n.cost;
		}	
	}//Node

}//class
