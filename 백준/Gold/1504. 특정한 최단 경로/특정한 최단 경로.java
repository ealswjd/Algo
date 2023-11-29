import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1504
public class Main {
	static final int INF = 200_000_002;
	static int N, E;
	static int[] target;
	static ArrayList<ArrayList<Node>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수		
		
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}//for
		
		int a, b, c;
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}//for
		
		st = new StringTokenizer(br.readLine());
		target = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		br.close();
		
		int[] ans = new int[2];
		ans[0] += move(1, target[0]);
		ans[0] += ans[1] = move(target[0], target[1]);
		ans[0] += move(target[1], N);
		
		ans[1] += move(1, target[1]);
		ans[1] += move(target[0], N);
		
		if(ans[0]>=INF && ans[1]>=INF) System.out.print(-1);
		else System.out.print(Math.min(ans[0], ans[1]));
	}//main
	
	private static int move(int from, int to) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		int[] cost = new int[N+1];
		Arrays.fill(cost, INF);
		cost[from] = 0;
		
		pq.offer(new Node(from, 0));
		Node cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			
			if(visited[cur.to]) continue;
			visited[cur.to] = true;
			
			for(Node next : list.get(cur.to)) {
				if(cost[next.to] > cur.cost + next.cost) {
					cost[next.to] = cur.cost + next.cost;
					pq.offer(new Node(next.to, cost[next.to]));
				}//if
			}//for
		}//while
		
		return cost[to];
	}//move

	static class Node implements Comparable<Node> {
		int to;
		int cost;
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}//Node

}//class