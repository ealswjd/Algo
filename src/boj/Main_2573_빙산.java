package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2573_빙산 {
	static int N, M;
	static int[][] map, tmpMap;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		tmpMap = new int[N][M];
		
		Queue<int[]> q = new LinkedList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = tmpMap[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) q.offer(new int[] {i, j, 0, map[i][j]});
			}//for j
		}//for i
		br.close();
		
		System.out.print( bfs(q) );
	}//main

	private static int bfs(Queue<int[]> q) {
		int r, c, year=0, h;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			r = now[0];
			c = now[1];
			if(year != now[2]) {
				copyMap();
				boolean[][] visited = new boolean[N][M];
				int lump=0;
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						if(map[i][j] > 0 && !visited[i][j])	{
							lump++;
							checkLump(i, j, visited);
						}
					}//for
				}//for				
				if(lump > 1) return now[2];
			}//if
			
			year = now[2];
			h = now[3];
			
			int cnt = 0;
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(checkRange(nr, nc) || map[nr][nc] > 0) continue;
				cnt++;
			}//for
			h -= cnt;
			tmpMap[r][c] = h;
			if(h > 0) q.offer(new int[] {r, c, year+1, h});
			
		}//while
		
		return 0;
	}//bfs


	private static void checkLump(int r, int c, boolean[][] visited) {
		visited[r][c] = true;
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(checkRange(nr, nc)) continue;
			if(visited[nr][nc] || map[nr][nc] <= 0) continue;
			checkLump(nr, nc, visited);
		}//for
		
	}//checkLump
	
	
	private static boolean checkRange(int nr, int nc) {
		return nr < 0 || nr >= N || nc < 0 || nc >= M;
	}//checkRange

	private static void copyMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = tmpMap[i][j];
			}//for
		}//for
		
	}//copyMap
	

}//class

/*

5 7
0 0 0 0 0 0 0
0 3 3 2 3 3 0
0 4 0 4 0 3 0
0 0 0 0 4 3 0
0 0 0 0 0 0 0
0


5 7
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0 0 10 0 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0


5 7
0 0 0 0 0 0 0
0 3 3 1 3 3 0
0 4 0 4 0 3 0
0 0 0 0 4 3 0
0 0 0 0 0 0 0
1


5 7
0 0 0 0 0 0 0
0 3 6 3 6 7 0
0 3 0 0 0 10 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
2


5 7
0 0 0 0 0 0 0
0 3 6 0 6 7 0
0 3 0 0 0 10 0
0 0 0 0 0 0 0
0 0 0 0 0 0 0
0


5 7
0 0 0 0 0 0 0
0 9 8 9 8 9 0
0 9 8 9 8 9 0
0 9 8 9 8 9 0
0 0 0 0 0 0 0
0


5 7
0 0 0 0 0 0 0
0 9 8 3 8 9 0
0 9 8 0 8 9 0
0 9 8 9 8 9 0
0 0 0 0 0 0 0
5


5 5
0 0 0 0 0
0 1 0 1 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0

7 7
0 0 0 0 0 0 0
0 1 1 0 1 1 0
0 1 9 1 9 1 0
0 1 1 1 1 1 0
0 1 1 1 1 1 0
0 0 1 1 1 0 0
0 0 0 0 0 0 0
2

*/
