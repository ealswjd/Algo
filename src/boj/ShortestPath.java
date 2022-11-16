package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* https://www.acmicpc.net/problem/1753
 * [1753번] 최단 경로 > 최단경로  */
public class ShortestPath {
	static List<ArrayList<Node>> list = new ArrayList<>();
	static int INF = Integer.MAX_VALUE;
	static int[] minArr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		
		minArr = new int[V+1];
		Arrays.fill(minArr, INF);		
		for(int i=0; i<=V; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list.get(u).add(new Node(v, w));
		}		
		
		dijkstra(start);
		
		StringBuilder answer = new StringBuilder();
		for(int i=1; i<V+1; i++) {
			if(minArr[i] < INF) answer.append(minArr[i] + "\n");
			else answer.append("INF" + "\n");
		}
		
		System.out.println(answer);		
	}//main
	
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		minArr[start] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int idx = node.getIndex();
			int cost = node.getCost();

			if(minArr[idx] < cost) continue;
			List<Node> nodes = list.get(idx);
			for(Node n : nodes) {
				int nCost = cost + n.getCost();
				if(nCost < minArr[n.getIndex()]) {
					minArr[n.getIndex()] = nCost;
					pq.offer(new Node(n.getIndex(), nCost));
				}
			}
		}
	}//dijkstra

}//class

class Node implements Comparable<Node>{
	private int idx;
	private int cost;
	
	public Node(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
	
	public int getIndex() { return idx; }
	public int getCost() { return cost; }
	
	@Override
	public int compareTo(Node n) {
		return this.cost - n.cost;
	}	
}//class Node
