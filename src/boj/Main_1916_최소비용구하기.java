package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1916_최소비용구하기 {
	static int N, M, S, E;
	static int[] minArr;
	static ArrayList<ArrayList<Bus>> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 도시의 개수
		M = Integer.parseInt(br.readLine()); // 버스의 개수
		minArr = new int[N+1];
		
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}//for

		StringTokenizer st;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(start).add(new Bus(start, end, cost));
		}//for
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		br.close();
		
		Arrays.fill(minArr, 987654321);
		int cost = getMinCost();
		System.out.print(cost);
	}//main
	
	private static int getMinCost() {
		Bus bus;
		PriorityQueue<Bus> pq = new PriorityQueue<>();
		for(int i=0; i<list.get(S).size(); i++) {
			pq.offer(list.get(S).get(i));
		}//for
		minArr[S] = 0;
		
		int start, end, cost;
		while(!pq.isEmpty()) {
			bus = pq.poll();
			start = bus.start;
			end = bus.end;
			cost = bus.cost;

			if(minArr[start]+cost < minArr[end]) {
				minArr[end] = minArr[start]+cost;
				for(int i=0,size=list.get(end).size(); i<size; i++) {
					pq.offer(list.get(end).get(i));
				}//for
			}//if
		}//while
		
		return minArr[E];
	}//getMinCost


	static class Bus implements Comparable<Bus>{
		int start;
		int end;
		int cost;
		public Bus(int start, int end, int cost) {
			super();
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		@Override
		public int compareTo(Bus o) {
			return this.cost - o.cost;
		}
	}//Bus

}//class
