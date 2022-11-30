package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1743_음식물피하기 {
	static int N, M, max;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0 ,0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 첫째 줄에 통로의 세로 길이 N(1 ≤ N ≤ 100)과 가로 길이 M(1 ≤ M ≤ 100) 
		// 그리고 음식물 쓰레기의 개수 K(1 ≤ K ≤ N×M)이 주어진다. 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		// 다음 K개의 줄에 음식물이 떨어진 좌표 (r, c)가 주어진다.
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			map[r][c] = 1;
		}
		br.close();

		max = -1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					int cnt = dfs(i, j, 1);
					max = Math.max(max, cnt);
				}
			}//for j
		}//for i
		
		System.out.print(max);
	}//main

	private static int dfs(int r, int c, int cnt) {
		visited[r][c] = true;
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
			if(visited[nr][nc] || map[nr][nc] == 0) continue;
			cnt = dfs(nr, nc, cnt+1);
		}
		
		return cnt;
	}//dfs

}//class

/*

3 4 5
3 2
2 2
3 1
2 3
1 1
-----> 4

*/