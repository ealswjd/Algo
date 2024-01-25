import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1890
public class Main {
	static int N;
	static int[][] map;
	static long[][] dp;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new long[N][N];
		visited = new boolean[N][N];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}//for j
		}//for i
		br.close();

		visited[0][0] = true;
		getCnt(0, 0);
		System.out.print(dp[0][0]);
	}//main

	private static long getCnt(int r, int c) {		
		if(dp[r][c] != -1) return dp[r][c];
		if(r == N-1 && c == N-1) return 1;
		
		dp[r][c] = 0;
		if(!rangeCheck(r, c+map[r][c])) jump(r, c, r, c+map[r][c]); // 오른쪽
		if(!rangeCheck(r+map[r][c], c)) jump(r, c, r+map[r][c], c); // 아래
		
		return dp[r][c];
	}//getCnt

	private static void jump(int r, int c, int nr, int nc) {
		visited[nr][nc] = true;
		dp[r][c] += getCnt(nr, nc);
		visited[nr][nc] = false;		
	}//jump

	private static boolean rangeCheck(int r, int c) {
		return r >= N || c >= N || visited[r][c];
	}//rangeCheck

}//class