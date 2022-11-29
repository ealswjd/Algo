package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2606
public class Main_2606_바이러스 {
	static int N, result; // 컴퓨터의 수, 정답
	static ArrayList<ArrayList<Integer>> list;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		
		// 첫째 줄에는 컴퓨터의 수가 주어진다.
		N = Integer.parseInt(br.readLine());	
		visited = new boolean[N+1];
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		// 둘째 줄에는 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수가 주어진다.
		int cnt = Integer.parseInt(br.readLine());		
		// 이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.
		StringTokenizer st;
		for(int i=0; i<cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		br.close();
		// 1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.
		dfs(1);
		System.out.println(result-1);

	}//main

	private static void dfs(int v) {
		visited[v] = true;
		result++;
		
		for(int i=0; i<list.get(v).size(); i++) {
			int nv = list.get(v).get(i);
			if(!visited[nv]) dfs(nv);
		}
		
	}//dfs

}//class
