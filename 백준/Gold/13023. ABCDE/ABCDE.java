import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
		static int N, M, ans = 0;
	static boolean end;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		visited = new boolean[N];

		for(int i=0; i<N && !end; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			dfs(i, 1);
			visited[i] = false;
		}
		System.out.println(ans);

	}//main

	private static void dfs(int v, int cnt) {	

		if(cnt >= 5) {
			ans = 1;
			end = true;
			return;
		}
		for(int i=0, size=list.get(v).size(); i<size && !end; i++) {
			int idx = list.get(v).get(i);
			if(visited[idx]) continue;
			visited[idx] = true;
			dfs(idx, cnt+1);
			visited[idx] = false;
		}
		
	}

}//class