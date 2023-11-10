import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1937
public class Main {
	static int N;
	static int[][] map, dp;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		
		StringTokenizer st;
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		br.close();
		
		int max = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				max = Math.max(max, dfs(r, c));
			}//for c
		}//for r

		System.out.print(max);
	}//main

	private static int dfs(int r, int c) {
		if(dp[r][c] != 0) return dp[r][c];
		
		dp[r][c] = 1;
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc) || map[nr][nc] <= map[r][c]) continue;

			dp[r][c] = Math.max(dp[r][c], dfs(nr, nc)+1);
		}//for

		return dp[r][c];
	}//dfs

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=N || c<0 || c>=N;
	}//rangeCheck

}//class