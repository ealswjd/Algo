package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11724
public class Main_11724_연결요소의개수 {
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점의 개수 N
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수 M
		list = new ArrayList<>();
		visited = new boolean[N+1];
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list.get(a).add(b);
			list.get(b).add(a);
		}
		br.close();
		
		int cnt = 0;
		for(int v=1; v<=N; v++) {
			if(!visited[v]) {
				dfs(v);
				cnt++;
			}
		}
		
		System.out.print(cnt);

	}//main

	private static void dfs(int v) {
		visited[v] = true;
		
		for(int i=0; i<list.get(v).size(); i++) {
			int nv = list.get(v).get(i);
			if(!visited[nv]) dfs(nv);
		}
		
	}

}//class
