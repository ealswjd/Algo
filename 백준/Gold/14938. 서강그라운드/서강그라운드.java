import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14938
public class Main {
	static int N, M, R;
	static int[] itemCnt;
	static ArrayList<ArrayList<Node>> nodeList;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 지역의 개수
		M = Integer.parseInt(st.nextToken()); // 예은이의 수색범위
		R = Integer.parseInt(st.nextToken()); // 길의 개수
		
		itemCnt = new int[N]; // 각 구역에 있는 아이템의 수 
		nodeList = new ArrayList<>(); // 길
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			itemCnt[i] = Integer.parseInt(st.nextToken());
			nodeList.add(new ArrayList<>());
		}//for
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			// 길 양 끝에 존재하는 지역의 번호 a, b
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken()); // 길의 길이
			// 양방향 통행
			nodeList.get(a).add(new Node(b, cost)); 
			nodeList.get(b).add(new Node(a, cost));
		}//for
		
		int max = 0;
		pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			max = Math.max(max, getItem(i));			
		}//for
		System.out.print(max);
	}//main
	
	private static int getItem(int start) {
		int sum = 0;
		boolean[] visited = new boolean[N];
		int[] min = new int[N];
		Arrays.fill(min, 987654321);
		min[start] = 0;
		pq.offer(new Node(start, 0));
		
		Node cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			
			if(visited[cur.to]) continue;			
			visited[cur.to] = true;
			
			for(Node next : nodeList.get(cur.to)) {
				if(min[next.to] > min[cur.to] + next.cost && min[cur.to] + next.cost <= M) {
					min[next.to] = min[cur.to] + next.cost;
					pq.offer(next);
				}//if
			}//for
		}//while
		
		for(int i=0; i<N; i++) {
			if(min[i] <= M) sum += itemCnt[i];
		}//for
		
		return sum;
	}//getItem

	static class Node implements Comparable<Node> {
		int to; // 목적지
		int cost; // 길의 길이
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node n) {
			return this.cost - n.cost;
		}
	}//Node

}//class