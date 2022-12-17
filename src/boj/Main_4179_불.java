package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4179
public class Main_4179_불 {
	static int R, C; // 행, 열
	static char[][] map; // 미로
	static int[] start; // 지훈 시작 위치

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		boolean[][] visited = new boolean[R][C]; // 방문체크
		Queue<int[]> q = new LinkedList<>();
		for(int i=0; i<R; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				map[i][j] = tmp[j];
				if(tmp[j] == 'F') { // 불이면 큐에 담기, 방문체크
					q.offer(new int[] {i, j, 0, 0});
					visited[i][j] = true;
				}
				else if(tmp[j] == 'J') { // 지훈이는 시작 위치만 담고 방문 체크
					start = new int[] {i, j, 1, 0};
					visited[i][j] = true;
				}  
			}//for j
		}//for i
		br.close();
		
		// 불 다 담은 후에 지훈이 시작위치 넣어주기
		q.offer(start);
		System.out.print(bfs(visited, q));						
	}//main

	private static String bfs(boolean[][] visited, Queue<int[]> q) {
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		int r = 0, c = 0;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			r = tmp[0]; // 행
			c = tmp[1]; // 열
			int J = tmp[2]; // 지훈:1 불:0
			int time = tmp[3]; // 이동 시간
			for(int i=0; i<4; i++) {
				int nr = r + dr[i]; // 다음 행
				int nc = c + dc[i]; // 다음 열
				if(check(nr, nc) && J==1) return String.valueOf(time+1); // 지훈이가 탈출하면 탈출시간 리턴
				// 다음에 갈 수 있는지 체크
				if(check(nr, nc) || visited[nr][nc]  || map[nr][nc] == '#') continue;
				visited[nr][nc] = true; // 방문 체크
				q.offer(new int[] {nr, nc, J, time+1}); // 다음 위치 담기
			}//for
		}//while
		
		return "IMPOSSIBLE"; // 탈출 불가능
	}//bfs

	// 범위 체크
	private static boolean check(int nr, int nc) {
		return nr<0 || nr>=R || nc<0 || nc>=C;
	}//check

}//class

/*

4 4
####
#JF#
#..#
#..#

out : 3

*/