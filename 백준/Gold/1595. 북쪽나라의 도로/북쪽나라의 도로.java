import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1595
public class Main {
	static final int N = 10_000;
	static int result, max;
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
			visited[1] = true;
			dfs(1, 0);	
			
			visited = new boolean[N+1];		
			visited[max] = true;
			dfs(max, 0);	
			
			System.out.print(result);			
		}
	}//main

	private static void dfs(int cur, int cost) {
		
		for(int[] next : list.get(cur)) {
			if(visited[next[0]]) continue;
			int nCost = cost + next[1];
			visited[next[0]] = true;
			if(result < nCost) {
				max = next[0];
				result = nCost;
			}
			dfs(next[0], nCost);
		}//for
		
	}//dfs

}//class