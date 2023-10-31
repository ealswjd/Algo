import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1167
public class Main {
	static int V, result, max;
	static ArrayList<ArrayList<int[]>> nodeList;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());		
		
		nodeList = new ArrayList<>();
		for(int i=0; i<=V; i++) {
			nodeList.add(new ArrayList<>());
		}//for
		
		StringTokenizer st;
		int v, to, cost;
		for(int i=0; i<V; i++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			while(true) {
				to = Integer.parseInt(st.nextToken());
				if(to == -1) break;
				cost = Integer.parseInt(st.nextToken());
				nodeList.get(v).add(new int[] {to, cost});
			}//while
		}//for
		
		result = max = 0;
		visited = new boolean[V+1];
		dfs(1, 0);
		
		visited = new boolean[V+1];
		dfs(max, 0);

		System.out.print(result);
	}//main

	private static void dfs(int cur, int cost) {
		visited[cur] = true;
		
		if(result < cost) {
			result = cost;
			max = cur;
		}//if
		
		for(int[] next : nodeList.get(cur)) {
			if(visited[next[0]]) continue;
			dfs(next[0], next[1] + cost);
		}//for
		
	}//dfs

}//class