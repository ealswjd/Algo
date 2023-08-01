import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long[][] map;
	static long[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new long[N+1][N+1];
		dp = new long[3][N+1][N+1];
		
		StringTokenizer st;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		System.out.print( dfs(1, 2, 0) );

	}//main

	private static long dfs(int x, int y, int d) {
		if(x <= 0 || x > N || y <= 0 || y > N) return 0;
		if(d==2) {
			if(map[x-1][y] == 1 || map[x][y-1] == 1) return 0;
		}
		if(map[x][y] == 1) return 0;
		if(dp[d][x][y] != 0) return dp[d][x][y];
		
	
		if(x == N && y == N) return 1;
		
		if(d == 0) { // 가로
			dp[d][x][y] += dfs(x+1, y+1, 2);					
			dp[d][x][y] += dfs(x, y+1, 0);				
		}else if(d == 2) { // 대각선
			dp[d][x][y] += dfs(x, y+1, 0);
			dp[d][x][y] += dfs(x+1, y+1, 2);					
			dp[d][x][y] += dfs(x+1, y, 1);					
		}else { // 세로
			dp[d][x][y] += dfs(x+1, y+1, 2);					
			dp[d][x][y] += dfs(x+1, y, 1);	
		}
		

		
		return dp[d][x][y];
		
	}//dfs

}//class