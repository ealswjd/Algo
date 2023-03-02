package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/16954
public class Main_16954_움직이는미로 {
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[8][8];
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {7, 0, 0}); /// 행, 열, 시간
		
		for(int i=0; i<8; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<8; j++) {
				map[i][j] = tmp[j];
			}//for
		}//for
		br.close();

		// 욱제의 캐릭터가 가장 오른쪽 윗 칸에 도착할 수 있으면 1, 없으면 0을 출력한다.
		System.out.print( bfs(q) );
	}//main

	private static int bfs(Queue<int[]> q) {
		int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1, 0}; // 상 하 좌 우 좌상 우상 좌하 우하 제자리
		int[] dc = {0, 0, -1, 1, -1, 1, -1, 1, 0};
		int[] now;
		int r, c, time=0;
		boolean[][] visited = new boolean[8][8]; // 방문체크
		
		while(!q.isEmpty()) {
			now = q.poll();
			r = now[0]; // 행
			c = now[1]; // 열 
			if(time != now[2]) {
				down(); // 벽 이동
				visited = new boolean[8][8]; // 방문 초기화
			}
			time = now[2]; // 시간
			if(map[r][c] == '#') continue;
			
			if(r==0 && c==7) return 1; // 미로 탈출
			
			int nr, nc;
			for(int i=0; i<9; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc) || map[nr][nc] == '#') continue;
				if(visited[nr][nc]) continue;
				q.offer(new int[] {nr, nc, time+1});
				visited[nr][nc] = true;
			}//for			
			
		}//while
		
		return 0; // 탈출 실패
	}//bfs

	private static void down() {
		for(int i = 7 ; i > 0 ; i--) {
            for(int j = 0 ; j < 8 ; j++) {
                map[i][j] = map[i - 1][j];
            }
        }
		Arrays.fill(map[0], '.');
	}//down

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= 8 || c < 0 || c >= 8;
	}//rangeCheck


}//class

/*

........
........
........
........
........
.#######
#.......
........
1

*/