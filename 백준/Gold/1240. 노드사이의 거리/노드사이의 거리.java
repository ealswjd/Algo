import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1240
public class Main {
	static boolean flag;
	static ArrayList<ArrayList<int[]>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 노드의 개수
		int M = Integer.parseInt(st.nextToken()); // 거리를 알고 싶은 노드 쌍의 개수
		
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}//for
		
		int a, b, dist;
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			dist = Integer.parseInt(st.nextToken());
			list.get(a).add(new int[] {b, dist});
			list.get(b).add(new int[] {a, dist});
		}//for
		
		StringBuilder ans = new StringBuilder();
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			flag = false;
			ans.append(dfs(a, b, 0, new boolean[N+1])).append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static int dfs(int from, int to, int sum, boolean[] visited) {
		visited[from] = true;
		if(from == to) {
			flag = true;
			return sum;
		}//if
		
		int tmp = 0;
		for(int[] next : list.get(from)) {
			if(visited[next[0]]) continue;
			tmp = dfs(next[0], to, sum+next[1], visited);
			if(flag) break;
		}//for
		
		return tmp;
	}//dfs

}//class