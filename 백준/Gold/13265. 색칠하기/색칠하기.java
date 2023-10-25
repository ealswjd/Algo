import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13265
public class Main {
	static int N;
	static final int RED=1, BLUE=2;
	static ArrayList<ArrayList<Integer>> list;
	static final String IMPOSSIBLE = "impossible"; 
	static final String POSSIBLE = "possible"; 
	static int[] colors;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			list = new ArrayList<>();
			for(int i=0; i<=N; i++) {
				list.add(new ArrayList<>());
			}//for
			
			while(M-->0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list.get(a).add(b);
				list.get(b).add(a);
			}//while
			
			colors = new int[N+1];
			boolean possible=true;
			for(int i=1; i<=N&&possible; i++) {
				if(colors[i] > 0) continue;
				colors[i] = RED;
				possible = dfs(i, RED); 
			}//for
			
			if(possible) ans.append(POSSIBLE).append("\n");
			else ans.append(IMPOSSIBLE).append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static boolean dfs(int cur, int color) {
		boolean possible = true;
		
		for(int next : list.get(cur)) {
			if(colors[next]==color) return false;
			if(colors[next] != 0) continue;
			colors[next] = color==RED?BLUE:RED;
			possible = dfs(next, colors[next]);
			if(!possible) return false;
		}//for
		
		return possible;
	}//dfs

}//class