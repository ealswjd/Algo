package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

//https://www.acmicpc.net/problem/2468
public class Main_2468_안전영역 {
	static int N;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		Set<Integer> set = new TreeSet<>();
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				set.add(map[i][j]);
			}
		}
		
		int max = 1;
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()) {
			int height = it.next();
			int cnt = 0;
			boolean[][] visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && map[i][j] > height) {
						dfs(i, j, visited, height);
						cnt++;
					}
				}
			}//for
			max = Math.max(max, cnt);
		}//while
		
		System.out.println(max);

	}//main

	private static void dfs(int r, int c, boolean[][] visited, int height) {
		visited[r][c] = true;
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if(visited[nr][nc] || map[nr][nc] <= height) continue;
			dfs(nr, nc, visited, height);
		}
		
	}//dfs

}//class
