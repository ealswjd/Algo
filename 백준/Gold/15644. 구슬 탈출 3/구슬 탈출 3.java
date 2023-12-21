import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15644
public class Main {
	static final int U=0, D=1, L=2, R=3;
	static final char EMPTY='.';
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	static int N, M;
	static char[][] map;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		int[] r, b;
		r = new int[2];
		b = new int[2];
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'R') {
					r[0] = i;
					r[1] = j;
					map[i][j] = EMPTY;
				}
				else if(map[i][j] == 'B') {
					b[0] = i;
					b[1] = j;
					map[i][j] = EMPTY;
				}
			}//for j
		}//for i
		br.close();
		
		Position result = bfs(r, b);
		if(result == null) System.out.print(-1);
		else {
			StringBuilder ans = new StringBuilder();
			int cnt = result.cnt;
			ans.append(cnt).append('\n');
			char c='.';
			for(int i=1; i<=cnt; i++) {
				switch (result.order[i]) {
				case U: c = 'U';					
					break;
				case D: c = 'D';					
				break;
				case L: c = 'L';					
				break;
				case R: c = 'R';					
				break;
				}
				ans.append(c);
			}//for
			System.out.print(ans);
		}//else
	}//main

	private static Position bfs(int[] r, int[] b) {
		boolean[][][][] visited = new boolean[N][M][N][M];
		visited[r[0]][r[1]][b[0]][b[1]] = true;
		Queue<Position> q = new LinkedList<>();
		for(int dir=0; dir<4; dir++) {
			q.offer(new Position(r[0], r[1], b[0], b[1], 0, dir, new int[11]));
		}//for
		
		Position cur;
		while(!q.isEmpty()) {
			cur = q.poll();
		
			if(cur.redR == -1 && cur.redC== -1) return cur;
			if(cur.cnt == 10 || cur.blueR == -1) continue;
			
			for(int i=0; i<4; i++) {
				Position next = getNext(cur, i);
				if(next == null) continue;
				if(next.redR!=-1 && next.redC!=-1 && next.blueR!=-1 && next.blueC!=-1) {
					if(visited[next.redR][next.redC][next.blueR][next.blueC]) continue;
					visited[next.redR][next.redC][next.blueR][next.blueC] = true;
				}
				next.order[cur.cnt+1] = i;
				q.offer(next);
			}//for
		}//while
		
		return null;
	}//bfs
	
	private static Position getNext(Position cur, int dir) {
		int rr = cur.redR;
		int rc = cur.redC;
		int br = cur.blueR;
		int bc = cur.blueC;
		
		boolean redFirst = getFirst(cur, dir);		
		int goal = 0;
		if(redFirst) { // 빨간 구슬 먼저 움직임

			while(true) {
				rr += dr[dir];
				rc += dc[dir];
				if(map[rr][rc] != EMPTY) {
					if(map[rr][rc] == 'O') {
						goal++;
						rr = -1;
						rc = -1;
						break;
					}//if
					rr -= dr[dir];
					rc -= dc[dir];
					break;
				}
			}//while
			
			if(rr != -1) map[rr][rc] = 'R';
			
			while(true) {
				br += dr[dir];
				bc += dc[dir];
				if(map[br][bc] != EMPTY) {
					if(map[br][bc] == 'O') {
						goal++;
						br = -1;
						bc = -1;
						break;
					}//if
					br -= dr[dir];
					bc -= dc[dir];
					break;
				}//if
			}//while
			
			if(rr != -1) map[rr][rc] = EMPTY;
			if(goal > 1) return null;
			return new Position(rr, rc, br, bc, cur.cnt+1, cur.dir, Arrays.copyOf(cur.order, 11));
		}else { // 파란 구슬 먼저 움직임
			while(true) {
				br += dr[dir];
				bc += dc[dir];
				if(map[br][bc] != EMPTY) {
					if(map[br][bc] == 'O') {
						goal++;
						br = -1;
						bc = -1;
						break;
					}//if
					br -= dr[dir];
					bc -= dc[dir];
					break;
				}//if
			}//while
			
			if(br != -1) map[br][bc] = 'B';
			
			while(true) {
				rr += dr[dir];
				rc += dc[dir];
				if(map[rr][rc] != EMPTY) {
					if(map[rr][rc] == 'O') {
						goal++;
						rr = -1;
						rc = -1;
						break;
					}//if
					rr -= dr[dir];
					rc -= dc[dir];
					break;
				}
			}//while			
			
			if(br != -1) map[br][bc] = EMPTY;
			if(goal > 1) return null;		
			return new Position(rr, rc, br, bc, cur.cnt+1, cur.dir, Arrays.copyOf(cur.order, 11));			
		}

	}//getNext

	private static boolean getFirst(Position cur, int dir) {
		switch (dir) {
		case U: // 상
			if(cur.redR > cur.blueR) return false; // 파랑이 더 위에 있음
			break;
		case D: // 하
			if(cur.redR < cur.blueR) return false; // 파랑이 더 아래에 있음
			break;
		case L: // 좌
			if(cur.redC > cur.blueC) return false; // 파랑이 더 좌측에 있음
			break;
		case R: // 우
			if(cur.redC < cur.blueC) return false; // 파랑이 더 우측에 있음
			break;
		}//switch
		
		return true;
	}//getFirst

	static class Position {
		int redR;
		int redC;
		int blueR;
		int blueC;
		int cnt;
		int dir;
		int[] order;
		public Position(int redR, int redC, int blueR, int blueC, int cnt, int dir, int[] order) {
			super();
			this.redR = redR;
			this.redC = redC;
			this.blueR = blueR;
			this.blueC = blueC;
			this.cnt = cnt;
			this.dir = dir;
			this.order = order;
		}		
	}//Position

}//class