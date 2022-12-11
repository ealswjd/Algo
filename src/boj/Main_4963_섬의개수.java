package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4963
public class Main_4963_섬의개수 {
	static int R, C;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			if(R==0) break; // 입력의 마지막 줄에는 0이 두 개 주어진다.
			
			map = new int[R][C];
			for(int i=0; i<R; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}//for j
			}//for i
			
			visited = new boolean[R][C];
			int cnt = 0;
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(visited[i][j] || map[i][j]==0) continue;
					visited[i][j] = true;
					bfs(i, j);
					cnt++;
				}//for j
			}//for i			
			
			sb.append(cnt).append("\n");
		}//while

		System.out.print(sb);
	}//main

	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			r = tmp[0];
			c = tmp[1];
			for(int i=0; i<8; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(check(nr, nc) || map[nr][nc]==0) continue;
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc});
			}//for
		}//while
		
	}//bfs

	private static boolean check(int nr, int nc) {
		return nr<0 || nr>=R || nc<0 || nc>=C || visited[nr][nc];
	}//bfs

}//class
