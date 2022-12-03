package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6593
public class Main_6593_상범빌딩 {
	static int L, R, C; // 층 수, 행과 열의 개수
	static char[][][] map; //상범 빌딩
	static int[][][] visited; // 방문체크
	static Position start, end; // 시작 지점과 출구

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken()); // 층
			R = Integer.parseInt(st.nextToken()); // 행
			C = Integer.parseInt(st.nextToken()); // 열
			if(L==0) break; // 입력의 끝은 L, R, C가 모두 0으로 표현
			
			visited = new int[L][R][C];
			map = new char[L][R][C];
			for(int l=0; l<L; l++) {
				for(int r=0; r<R; r++) {
					char[] tmp = br.readLine().toCharArray();
					for(int c=0; c<C; c++) {
						map[l][r][c] = tmp[c];
						if(tmp[c] == 'S') start = new Position(l, r, c);
						if(tmp[c] == 'E') end = new Position(l, r, c);
					}//for c
				}//for r
				br.readLine(); // 각 층 사이에는 빈 줄이 있음
			}//for l
			
			int time = bfs();
			if(time == -1) { // 탈출이 불가능
				sb.append("Trapped!");
			}else { // 탈출할 수 있다면
				sb.append("Escaped in "+time+" minute(s).");
			}
			sb.append("\n");
		}//while
		
		System.out.print(sb);
	}//main

	private static int bfs() {
		Queue<Position> q = new LinkedList<>();
		q.offer(start);
		visited[start.l][start.r][start.c] = 1;
		int[] dl = {0, 0, 0, 0, -1, 1};
		int[] dr = {-1, 1, 0, 0, 0, 0};
		int[] dc = {0, 0, -1, 1, 0, 0};
		
		Position pos;
		while(!q.isEmpty()) {
			pos = q.poll();
			int l = pos.l;
			int r = pos.r;
			int c = pos.c;
			if(l==end.l && r==end.r && c==end.c) return visited[l][r][c] -1;
			
			for(int i=0; i<6; i++) {
				int nl = l + dl[i];
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(check(nl, nr, nc)) continue;
				if(visited[nl][nr][nc] != 0 || map[nl][nr][nc] == '#') continue;
				q.offer(new Position(nl, nr, nc));
				visited[nl][nr][nc] = visited[l][r][c] + 1;
			}//for
			
		}//while
		
		return -1;
	}//bfs

	// 범위 체크
	private static boolean check(int nl, int nr, int nc) {
		return (nl < 0 || nl >= L || nr < 0 || nr >= R || nc < 0 || nc >= C);
	}//check

	static class Position{
		int l;
		int r;
		int c;
		public Position(int l, int r, int c) {
			super();
			this.l = l;
			this.r = r;
			this.c = c;
		}	
	}//Position
}//class
