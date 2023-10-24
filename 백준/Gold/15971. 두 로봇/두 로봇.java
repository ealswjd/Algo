import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15971
public class Main {
	static int N, R1, R2; // 방 개수, 로봇1 방, 로봇2 방
	static ArrayList<ArrayList<int[]>> list; // 방 연결 리스트
	static int[][] cost; // 연결 길이

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 방 개수
		R1 = Integer.parseInt(st.nextToken()); // 로봇 1 방
		R2 = Integer.parseInt(st.nextToken()); // 로봇 2 방
		
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}//for
		
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(a).add(new int[] {b, cost});
			list.get(b).add(new int[] {a, cost});
		}//for
		br.close();
		
		cost = new int[2][N+1];
		dfs(R1, 0, 0, new boolean[N+1]); // 로봇 1 탐사
		dfs(R2, 1, 0, new boolean[N+1]); // 로봇 2 탐사
		
		int min = Integer.MAX_VALUE;
		for(int i=1; i<=N; i++) {
			min = Math.min(min,  cost[0][i]+cost[1][i]); 
			for(int[] next : list.get(i)) {
				min = Math.min(min, cost[0][i] + cost[1][next[0]]);
			}//for
		}//for
		
		if(min == Integer.MAX_VALUE) min = 0;
		System.out.print(min);
	}//main

	private static void dfs(int cur, int idx, int sum, boolean[] visited) {
		cost[idx][cur] = sum; // 이동 길이
		visited[cur] = true;
		if(list.get(cur).size() == 0) return;
		
		for(int[] next : list.get(cur)) {
			if(visited[next[0]]) continue;
			dfs(next[0], idx, sum+next[1], visited);
		}//for
	}//dfs

}//class