package sewa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범검거 {
	static int N, M, R, C, L;
	static int[][] map;
	static int[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			sb.append("#"+tc+" ");
			
			// 세로 크기 N, 가로 크기 M, 맨홀 뚜껑이 위치한장소의 세로 위치 R, 가로 위치 C, 그리고 탈출 후 소요된 시간 L
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 세로 크기
			M = Integer.parseInt(st.nextToken()); // 가로 크기
			R = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑 세로 위치
			C = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑 가로 위치
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간
			map = new int[N][M];
			visited = new int[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[] {R, C});
			visited[R][C] = 1;
			
			int res = bfs(q);
			
			sb.append(res);
			sb.append("\n");
		}//for tc

		System.out.print(sb);
	}//main

	private static int bfs(Queue<int[]> q) {
		int cnt = 1;
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		int[][] pipe = {
				{0, 0, 0, 0},
				{1, 1, 1, 1},
				{1, 1, 0, 0},
				{0, 0, 1, 1},
				{1, 0, 0, 1},
				{0, 1, 0, 1},
				{0, 1, 1, 0},
				{1, 0, 1, 0},
		};
		int[] opp = {1, 0, 3, 2};
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0];
			int c = tmp[1];
			
			if(visited[r][c] == L) return cnt;
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] != 0) continue;
				if(pipe[map[r][c]][i] != 0 && pipe[map[nr][nc]][opp[i]] != 0) {
					if(pipe[map[r][c]][i] == pipe[map[nr][nc]][opp[i]]) {
						q.offer(new int[] {nr, nc});
						visited[nr][nc] = visited[r][c] + 1;
						cnt++;
					}
				}
			}
			
		}//while
		
		return cnt;
	}//bfs

}//class
