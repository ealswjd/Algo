package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14500
// 14500. 테트로미노
// 정사각형 4개를 이어 붙인 폴리오미노
public class Main_14500_테트로미노 {
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
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, map[i][j]);				
				visited[i][j] = false;
				otherCheck(i, j);
			}
		}
		System.out.print(max);
	}//main

	private static void otherCheck(int x, int y) {

		if(x-1 >= 0 && y-1 >= 0 && y+1 < M) { // ㅗ
			max = Math.max(max, map[x][y-1] + map[x][y] + map[x][y+1] + map[x-1][y]) ;
		}
		
		if(x+1 < N && y-1 >= 0 && y+1 < M) { // ㅜ
			max = Math.max(max, map[x][y-1] + map[x][y] + map[x+1][y] + map[x][y+1]) ;
		}
		
		if(x-1 >= 0 && x+1 < N && y+1 < M) { // ㅏ
			max = Math.max(max, map[x-1][y] + map[x][y] + map[x][y+1] + map[x+1][y]) ;
		}
		
		if(x-1 >= 0 && x+1 < N && y-1 >= 0) { // ㅓ
			max = Math.max(max, map[x-1][y] + map[x][y] + map[x][y-1] + map[x+1][y]) ;
		}
		
		
	}//otherCheck

	private static void dfs(int x, int y, int cnt, int sum) {
		if(cnt == 4) {
			max = Math.max(max, sum);
			return;
		}

		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
			visited[nx][ny] = true;
			dfs(nx, ny, cnt+1, sum+map[nx][ny]);
			visited[nx][ny] = false;
		}
		
	}//dfs

}//class

/*

5 5
1 2 3 4 5
5 4 3 2 1
2 3 4 5 6
6 5 4 3 2
1 2 1 2 1

=> 19

*/