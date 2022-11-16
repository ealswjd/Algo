package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5427_불 {
	static char[][] map; // 건물 정보
	static int W, H; // 열, 행
	static int[] start; // 상근이 위치
	static boolean[][] visited; // 방문체크

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine()); 
		
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		while(tc-->0) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			Queue<int[]> q = new LinkedList<>();
			map = new char[H+2][W+2];
			visited = new boolean[H+2][W+2];
			for(int i=1; i<=H; i++) {
				char[] tmp = br.readLine().toCharArray();
				for(int j=1; j<=W; j++) {
					map[i][j] = tmp[j-1];
					if(map[i][j] == '*') { // 불
						q.offer(new int[] {i, j, 0, 0}); // 불 먼저 넣어
						visited[i][j] = true;
					}
					else if(map[i][j] == '@') { // 상근이
						start = new int[] {i, j, 1, 0}; // 상근이는 위치 저장만 해두고
						visited[i][j] = true;
					}
				}
			}
			
			q.offer(start); // 불 다 넣고나서 넣어주기
			ans.append( bfs(q) );
			ans.append("\n");
		}//while

		System.out.print(ans);
	}//main

	
//	'.': 빈 공간
//	'#': 벽
//	'@': 상근이의 시작 위치
//	'*': 불
	private static String bfs(Queue<int[]> q) {
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		int R = H+1; // 테두리 
		int C = W+1; // 테두리
		
		int r, c, s, time;
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			r = tmp[0]; // 행
			c = tmp[1]; // 열
			s = tmp[2]; // 상근=1, 불=0
			time = tmp[3]; // 이동 시간
			
			// 상근이가 탈출에 성공했는지 확인
			if(s==1 && (r==0 || r==R || c==0 || c==C)) 
				return String.valueOf(time);
			
			// 사방탐색
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length || visited[nr][nc]) continue;
				if(map[nr][nc] == '#'
						|| ( s==0 && (nr==0 || nr==R || nc==0 || nc==C)) ) continue;
				
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc, s, time+1});
				
			}//for
		}//while
		
		return "IMPOSSIBLE"; // 탈출 못했어
	}//bfs


}//class

/*

5
4 3
####
#*@.
####
7 6
###.###
#*#.#*#
#.....#
#.....#
#..@..#
#######
7 4
###.###
#....*#
#@....#
.######
5 5
.....
.***.
.*@*.
.***.
.....
3 3
###
#@#
###

-------->

2
5
IMPOSSIBLE
IMPOSSIBLE
IMPOSSIBLE

*/