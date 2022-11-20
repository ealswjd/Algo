package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1976_여행가자 {
	static int N, M; // 도시의 수 N, 여행 계획에 속한 도시들의 수 M
	static ArrayList<ArrayList<Integer>> list; // 경로 연결 정보

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		StringTokenizer st;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					list.get(i).add(j);
				}
			}//for j
		}//for i
		
		int[] order = new int[M]; // 여행경로
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean flag = true; // 갈 수 있다
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<M; i++) {
			q.offer(order[i-1]); // 현재 도시 넣어주고
			flag = bfs(q, order[i]);
			if(!flag) break; // 다음도시 못가면 멈춰
		}
		
		System.out.print(flag ? "YES" : "NO");
	}//main

	private static boolean bfs(Queue<Integer> q, int city) { // 현재 위치가 담긴 큐, 다음 도시
		boolean[] visited = new boolean[N+1]; // 방문체크
		visited[q.peek()] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll(); // 현재 도시
			if(cur == city) return true; // 다음 도시 갈 수 있으면 그만
			
			for(int i=0; i<list.get(cur).size(); i++) {
				int next = list.get(cur).get(i);
				if(visited[next]) continue;
				q.offer(next);
				visited[next] = true;
			}
		}//while
		
		return false; // 다음 도시 못가
	}//bfs

}//class

/*

3
3
0 1 0
1 0 1
0 1 0
1 2 3

--> YES

*/