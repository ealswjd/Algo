package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6087
public class Main_6087_레이저통신 {
	static int W, H;
	static char[][] map; // 지도
	static int[][][] visited; // 방문체크

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		int[] start = null; // 시작 C
		int[] end = null; // 도착 C
		map = new char[H][W];
		visited = new int[H][W][2]; // 방문체크 [행][열][개수,방향]
		
		for(int i=0; i<H; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<W; j++) {
				map[i][j] = tmp[j];
				if(tmp[j] == 'C') {
					if(start == null) start = new int[] {i, j};
					else end = new int[] {i, j};
				}
			}//for
		}//for
		br.close();
		
		map[start[0]][start[1]] = '*'; // 시작 C -> *로 변경
		System.out.print( bfs(start, end) );
	}//main
	
	// 거울 개수의 최솟값 구하기
	private static int bfs(int[] start, int[] end) {
		// 상하좌우
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		Queue<int[]> q = new LinkedList<>();
		
		// 방문배열 거울 개수 초기화
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				Arrays.fill(visited[i][j], 98765432);
			}
		}

		int[] now;
		int r, c, cnt;
		for(int i=0; i<4; i++) { // 출발점 기준 상하좌우 먼저 넣고
			r = start[0] + dr[i];
			c = start[1] + dc[i];
			if(r < 0 || r >= H || c < 0 || c >= W || map[r][c]=='*') continue;
			q.offer(new int[] {r, c, 0, i}); // 행, 열, 거울 개수, 방향			
		}
		visited[start[0]][start[1]][0] = -1;
		
		while(!q.isEmpty()) {
			now = q.poll(); // 현재 상태
			r = now[0]; // 행
			c = now[1]; // 열
			cnt = now[2]; // 거울 개수
			int d = now[3]; // 방향
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i]; // 다음 행
				int nc = c + dc[i]; // 다음 열
				// 범위체크
				if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
				// 방문 체크 & 벽 체크 & 방향 체크
				if(visited[nr][nc][0] < cnt || map[nr][nc]=='*' 
						|| (visited[nr][nc][0]<=cnt && visited[nr][nc][1]==i)) continue;
				
				// 현재 방향이랑 다르면
				if(i!=d) {
					if(visited[nr][nc][0] < cnt+1) continue;
					q.offer(new int[] {nr, nc, cnt+1, i}); // 거울 설치
					visited[nr][nc][0] = cnt+1;				
				} else { // 현재 방향이랑 같으면
					q.offer(new int[] {nr, nc, cnt, i});
					visited[nr][nc][0] = cnt;				
				}
				// 방문체크
				visited[nr][nc][1] = i;				
			}//for
			
		}//while
		
		return visited[end[0]][end[1]][0]; // 거울 최소 개수 반환
	}//bfs


}//class

/*

7 8
.......
......C
......*
*****.*
....*..
....*..
.C..*..
.......

 */