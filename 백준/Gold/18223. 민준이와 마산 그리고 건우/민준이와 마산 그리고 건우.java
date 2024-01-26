import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18223
public class Main {
	static final String SAVE="SAVE HIM", GOODBYE="GOOD BYE";
	static final int INF=987654321;
	static int V, E, P;
	static ArrayList<ArrayList<Node>> list;
	static int[] min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수
		P = Integer.parseInt(st.nextToken()); // 건우가 위치한 정점
		
		list = new ArrayList<>();
		for(int i=0; i<=V; i++) {
			list.add(new ArrayList<>());
		}//for
		
		int a, b, c;
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			// 양방향 그래프 형태
			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}//for
		br.close();

		min = new int[V+1];
		
		int minCost = getRoute(1, V);
		int pCost = getRoute(1, P);
		int vCost = getRoute(P, V);
		
		if(minCost == pCost + vCost) System.out.print(SAVE);
		else System.out.print(GOODBYE);
	}//main
	
	private static int getRoute(int start, int end) {
		if(start == end) return 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		Arrays.fill(min, INF);
		
		Node cur;
		int v, cost;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			v = cur.v;
			cost = cur.cost;
			
			for(Node next : list.get(v)) {
				if(min[next.v] > cost + next.cost) {
					min[next.v] = cost + next.cost;
					pq.offer(new Node(next.v, min[next.v]));
				}//id
			}//for
		}//while
		
		return min[end];
	}//getRoute

	static class Node implements Comparable<Node> {
		int v;
		int cost;
		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}//Node

}//class