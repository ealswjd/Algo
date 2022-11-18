package sewa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_4193_수영대회결승 {
	static int N;
	static int[][] map;
	static int[] start, end;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			sb.append("#"+tc+" ");
			N = Integer.parseInt(br.readLine());			
			
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 2) map[i][j] = 5;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			start = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			st = new StringTokenizer(br.readLine());
			end = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			int result = bfs();
			
			sb.append(String.valueOf(result));
			sb.append("\n");
		}//for tc

		System.out.print(sb);
	}//main

	private static int bfs() {
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		Queue<int[]> q = new LinkedList<>();
		int r = start[0], c = start[1], endR = end[0], endC = end[1];
		boolean[][] visited = new boolean[N][N];
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		int result = 0;
		
		int size;
		while(!q.isEmpty()) {
			size = q.size();
			while(--size>=0) {
				int[] tmp = q.poll();
				r = tmp[0];
				c = tmp[1];
				if(r == endR && c == endC) return result;
				
				for(int i=0; i<4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if(visited[nr][nc] || map[nr][nc] == 1) continue;
					
					if(map[nr][nc] == 0 || map[nr][nc] == 3) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});						
					}else {
						q.offer(new int[] {r, c});
					}
				}//for
			}//while
			init();
			result++;
		}//while
		
		return -1;
	}//bfs

	private static void init() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] > 3) map[i][j]--;
				else if(map[i][j] == 3) map[i][j] += 2;
			}
		}
		
	}//init

}//class

/*

3
5
0 0 0 0 0
0 0 0 1 0
0 0 0 1 0
2 2 1 1 0
0 0 0 0 0
4 0
2 0
6
0 0 0 0 0 0
0 1 1 0 0 0
0 0 0 1 2 0
1 1 0 1 0 1
0 0 0 1 0 1
0 0 0 2 0 1
5 0
2 5
6
0 0 0 0 0 0
0 0 0 0 0 0
1 0 1 1 1 0
1 0 0 0 0 0
1 0 1 1 1 0
0 0 2 0 2 0
5 0
3 5

#1 4
#2 10
#3 7

*/
