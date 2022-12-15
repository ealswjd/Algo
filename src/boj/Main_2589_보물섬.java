package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/2589
public class Main_2589_보물섬 {
	static int R, C, max; // 행, 열, 이동하는 시간
	static char[][] map; // 보물섬 지도

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C]; // 보물섬 지도
		
		ArrayList<Land> landList = new ArrayList<>();
		for(int i=0; i<R; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				map[i][j] = tmp[j];
				if(map[i][j] == 'L') landList.add(new Land(i, j));
			}
		}//for
		br.close();
		
		max = -1;
		for(Land start : landList) {
			bfs(start);
		}//for i
		
		System.out.print(max);
	}//main

	private static void bfs(Land start) {
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		int[][] visited = new int[R][C];
		int r = start.r;
		int c = start.c;
		visited[r][c] = 1;
		Queue<Land> q = new LinkedList<>();
		q.offer(start);
		
		Land land;
		while(!q.isEmpty()) {
			land = q.poll();
			r = land.r;
			c = land.c;
			int len = visited[r][c]; // 이동 시간
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(check(nr, nc) || visited[nr][nc] != 0) continue;
				visited[nr][nc] = len+1;
				q.offer(new Land(nr, nc));	
				max = Math.max(max, len); // 가장 긴 시간이 걸리는 곳 체크
			}//for
		}//while
		
	}//bfs

	// 다음지점에 갈 수 있는지 체크
	private static boolean check(int nr, int nc) {
		return nr<0 || nr>=R || nc<0 || nc>=C || map[nr][nc]=='W' ; 
	}//check

	static class Land{
		int r;
		int c;
		public Land(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}		
	}//Land
}//class
/*

5 7
WLLWWWL
LLLWLLL
LWLWLWW
LWLWLLL
WLLWLWW

out : 8

*/