import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17090
public class Main {
	static int N, M, cnt;
	static int[][] map;
	static int[][] dp;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static final int INF = 987654321;
	static String dir = "URDL";

	public static void main(String[] args) throws Exception {
		init();
		escape();
		System.out.print(cnt);
	}//main

	private static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		cnt = 0;		
		
		for(int i=0; i<N; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				map[i][j] = dir.indexOf(tmp[j]);
			}
			Arrays.fill(dp[i], INF);
		}//for
		br.close();

	}//init

	private static void escape() {
		boolean[][] visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					dp[i][j] = dfs(i, j, visited);				
				}//if
				cnt += dp[i][j];
			}//for j
		}//for i
		
	}//escape

	private static int dfs(int r, int c, boolean[][] visited) {		
		int idx = map[r][c];
		int nr = r + dr[idx];
		int nc = c + dc[idx];
		
		if(rangeCheck(nr, nc)) return dp[r][c] = 1;
		if(visited[nr][nc]) {
			if(dp[nr][nc] != INF) return dp[r][c] = dp[nr][nc];
			return dp[r][c] = 0;
		}//if
		
		visited[nr][nc] = true;
		
		return dp[r][c] = dfs(nr, nc, visited);
	}//dfs

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}//rangeCheck

}//class