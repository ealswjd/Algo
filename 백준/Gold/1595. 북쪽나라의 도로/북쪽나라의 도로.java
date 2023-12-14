import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1595
public class Main {
	static final int N = 10_000;
	static int max;
	static boolean[] visited;
	static ArrayList<ArrayList<int[]>> list;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}//for
		
		int a, b, cost, tmp=-1;
		while(true) {
			try {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());	
				tmp = a;
			} catch (Exception e) {
				break;
			}
			
			list.get(a).add(new int[] {b, cost});
			list.get(b).add(new int[] {a, cost});
		}//while		

		if(tmp == -1) System.out.print(0);
		else {
			visited = new boolean[N+1];		
			for(int i=1; i<=N; i++) {
				dfs(i, 0);	
			}//for
			
			System.out.print(max);			
		}
	}//main

	private static void dfs(int cur, int cost) {
		visited[cur] = true;
		
		for(int[] next : list.get(cur)) {
			if(visited[next[0]]) max = Math.max(max, cost);		
			else dfs(next[0], next[1] + cost);				
		}//for
		
		visited[cur] = false;
	}//dfs

}//class