import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14500
public class Main {

	static int N, M, max=Integer.MIN_VALUE;
	static int[][] map; // N×M인 종이
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M]; // 종이
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, map[i][j]);				
				visited[i][j] = false;
				otherCheck(i, j);
			}//for j
		}//for i
		System.out.print(max);
	}//main

	// 나머지 확인
	private static void otherCheck(int r, int c) {

		if(r-1 >= 0 && c-1 >= 0 && c+1 < M) { // ㅗ
			max = Math.max(max, map[r][c-1] + map[r][c] + map[r][c+1] + map[r-1][c]) ;
		}
		
		if(r+1 < N && c-1 >= 0 && c+1 < M) { // ㅜ
			max = Math.max(max, map[r][c-1] + map[r][c] + map[r+1][c] + map[r][c+1]) ;
		}
		
		if(r-1 >= 0 && r+1 < N && c+1 < M) { // ㅏ
			max = Math.max(max, map[r-1][c] + map[r][c] + map[r][c+1] + map[r+1][c]) ;
		}
		
		if(r-1 >= 0 && r+1 < N && c-1 >= 0) { // ㅓ
			max = Math.max(max, map[r-1][c] + map[r][c] + map[r][c-1] + map[r+1][c]) ;
		}		
		
	}//otherCheck

	private static void dfs(int r, int c, int cnt, int sum) {
		if(cnt == 4) {
			max = Math.max(max, sum);
			return;
		}//if

		for(int i=0; i<4; i++) {
			int nr = r + dx[i];
			int nc = c + dy[i];
			if(rangeCheck(nr, nc)) continue;
			visited[nr][nc] = true;
			dfs(nr, nc, cnt+1, sum+map[nr][nc]);
			visited[nr][nc] = false;
		}//for
		
	}//dfs

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M || visited[r][c];
	}//rangeCheck

}//class