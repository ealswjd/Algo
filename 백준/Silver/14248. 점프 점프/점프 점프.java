import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14248
public class Main {
	static int N, cnt;
	static int[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		visited = new boolean[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//for
		
		int s = Integer.parseInt(br.readLine());
		br.close();
		
		cnt = 1;
		visited[s] = true;
		dfs(s);
		System.out.print(cnt);
	}//main

	private static void dfs(int cur) {
		int r = cur + arr[cur];
		int l = cur - arr[cur];
		
		if(r <= N && !visited[r]) {
			visited[r] = true;
			cnt++;
			dfs(r);
		}
		
		if(l > 0 && !visited[l]) {
			visited[l] = true;
			cnt++;
			dfs(l);
		}
	
	}//dfs

}//class