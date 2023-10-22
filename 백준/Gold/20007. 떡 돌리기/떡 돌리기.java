import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20007
public class Main {
	static int N, X, S;
	static int[] min;
	static ArrayList<ArrayList<Node>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 집의 개수
		int M = Integer.parseInt(st.nextToken()); // 양방향 도로 개수
		X = Integer.parseInt(st.nextToken()); // 최대 거리
		S = Integer.parseInt(st.nextToken()); // 성현이네 집
		
		list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());
		}//for
		
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}//while
		br.close();		
		
		dijkstra();
		int day = getDay();
		System.out.print(day);
	}//main

	private static int getDay() {
		Arrays.sort(min);
		// 방문 못함
		if(min[N-1] > X) return -1;
		
		int day=0, num=0, sum=0;
		while(num<N) {
			while(num<N && sum+min[num] * 2 <= X) sum += min[num++] * 2;
			sum = 0;
			day++;
		}//while
		
		return day;
	}//getDay

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(S, 0));
		boolean[] visited = new boolean[N];
		min = new int[N];
		Arrays.fill(min, X+1);
		min[S] = 0;
		
		Node cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			
			if(visited[cur.to]) continue;
			visited[cur.to] = true;
			
			for(Node next : list.get(cur.to)) {
				if(!visited[next.to] && min[next.to] > min[cur.to] + next.dist && min[cur.to] + next.dist <= X) {
					min[next.to] = min[cur.to] + next.dist;
					pq.offer(new Node(next.to, min[next.to]));
				}//if
			}//for
		}//while

	}//dijkstra
	
	static class Node implements Comparable<Node> {
		int to;
		int dist;
		public Node(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node n) {
			return this.dist - n.dist;
		}
	}//Node

}//class