import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25195
public class Main {
	static int N, M; // 정점의 개수, 간선의 개수
	static ArrayList<ArrayList<Integer>> list;
	static Set<Integer> fan; // 팬클럽 곰곰이
	static boolean yes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수

		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}//for

		// 간선의 정보
		int u, v;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			list.get(u).add(v);
		}//for
		
		// 팬클럽 곰곰이
		fan = new HashSet<>();
		int cnt = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<cnt; i++) {
			fan.add(Integer.parseInt(st.nextToken()));
		}//for
		br.close();

		dfs(1); // 1번 정점에서 출발
		
		if(yes) System.out.print("yes"); // 팬클럽 만나지 않고 이동 가능
		else System.out.print("Yes"); // 어떻게 이동하더라도 팬클럽 만나
	}//main

	private static void dfs(int cur) {
		if(yes || fan.contains(cur)) return;
		if(list.get(cur).size() == 0) {
			yes = true;
			return;
		}//if
		
		for(int next : list.get(cur)) {
			dfs(next);
		}//for
	}//dfs

}//class