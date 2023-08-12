import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17090
public class Main {
	static int N, M;
	static char[][] map;
	static int[][] dp;
	static int cnt;
	static final int INF = 987654321;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static Map<Character, Integer> dist;

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
		map = new char[N][M];
		dp = new int[N][M];
		cnt = 0;		
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			Arrays.fill(dp[i], INF);
		}//for
		br.close();
		
		dist = new HashMap<Character, Integer>();
		dist.put('U', 0); // 상
		dist.put('R', 1); // 우
		dist.put('D', 2); // 하
		dist.put('L', 3); // 좌
	}//init

	private static void escape() {
		boolean[][] visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]) continue;
				visited[i][j] = true;
				dfs(i, j, visited);
			}//for j
		}//for i
		
	}//escape

	private static int dfs(int r, int c, boolean[][] visited) {		
		int idx = dist.get(map[r][c]);
		int nr = r + dr[idx];
		int nc = c + dc[idx];
		if(rangeCheck(nr, nc)) {
			cnt++;
			return dp[r][c] = 1;
		}
		
		if(!visited[nr][nc]) {
			visited[nr][nc] = true;
			dp[r][c] = dfs(nr, nc, visited); 
		}else {
			dp[r][c] = dp[nr][nc];
		}
		
		if(dp[r][c] == INF) dp[r][c] = 0;
		cnt += dp[r][c];
		return dp[r][c];
	}//dfs

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}//rangeCheck


}//class