import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/11559
public class Main {
	static final int H=12, W=6;
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	static char[][] map = new char[H][W];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<H; i++) {
			map[i] = br.readLine().toCharArray();
		}//for
		br.close();
		
		PuyoPuyo();

	}//main

	private static void PuyoPuyo() {
		int cnt = 0;
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited;
		boolean flag;
		
		while(true) {
			visited = new boolean[H][W];
			flag = false;
			
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					if(!visited[i][j] && map[i][j] != '.') {
						char color = map[i][j];
						dfs(i, j, color, q, visited);
						if(q.size() >= 4) {
							flag = true;
							q.clear();
						}else {
							int[] cur;
							while(!q.isEmpty()) {
								cur = q.poll();
								map[cur[0]][cur[1]] = color;
							}//while
						}//else
					}//if
				}//for j
			}//for i
			
			if(!flag) break;
			else {
				cnt++;
				down();
			}
		}//while
		
		System.out.print(cnt);
	}//PuyoPuyo

	private static void down() {
		int nr;
		for(int c=0; c<W; c++) {
			for(int r=H-1; r>=0; r--) {
				if(map[r][c] != '.') {
					nr = r+1;
					while(!rangeCheck(nr, c) && map[nr][c] == '.') {
						map[nr][c] = map[nr-1][c];
						map[nr-1][c] = '.';
						nr++;
					}//while
				}
			}//for r
		}//for c
		
	}//down

	private static void dfs(int r, int c, char color, Queue<int[]> q, boolean[][] visited) {
		visited[r][c] = true;
		q.offer(new int[] {r, c});
		map[r][c] = '.';
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc) || visited[nr][nc] || map[nr][nc] != color) continue;
			dfs(nr, nc, color, q, visited);
		}//for
		
	}//dfs

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=H || c<0 || c>=W;
	}//rangeCheck

}//class