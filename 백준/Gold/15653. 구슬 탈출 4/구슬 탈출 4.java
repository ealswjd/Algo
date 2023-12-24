import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int U=0, D=1, L=2, R=3;
	static final char EMPTY='.';
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	static int N, M, or, oc;
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
				}else if(map[i][j] == 'O') {
					or = i;
					oc = j;
				}
			}//for j
		}//for i
		br.close();
		
		int result = bfs(r, b);
		System.out.print(result);
	}//main

	private static int bfs(int[] r, int[] b) {
		boolean[][][][] visited = new boolean[N][M][N][M];
		visited[r[0]][r[1]][b[0]][b[1]] = true;
		Queue<Position> q = new LinkedList<>();
		q.offer(new Position(r[0], r[1], b[0], b[1], 0));
		
		Position cur;
		while(!q.isEmpty()) {
			cur = q.poll();
		
			if(cur.redR == or && cur.redC== oc) return cur.cnt;
			if(cur.blueR == or && cur.blueC == oc) continue;
			
			for(int i=0; i<4; i++) {
				Position next = getNext(cur, i);
				if(next == null) continue;
				if(visited[next.redR][next.redC][next.blueR][next.blueC]) continue;
				visited[next.redR][next.redC][next.blueR][next.blueC] = true;
				q.offer(next);
			}//for
		}//while
		
		return -1;
	}//bfs

	private static Position getNext(Position cur, int dir) {
		int rr = cur.redR;
		int rc = cur.redC;
		int br = cur.blueR;
		int bc = cur.blueC;

		boolean isRed = redFirst(cur, dir);
		// 빨간 구슬 먼저 움직임	
		if(isRed) return move(rr, rc, br, bc, 'R', dir, cur, isRed);
		// 파란 구슬 먼저 움직임	
		else return move(br, bc, rr, rc, 'B', dir, cur, isRed);	

	}//getNext

	private static Position move(int r1, int c1, int r2, int c2, char c, int dir, Position cur, boolean isRed) {
		int goal = 0;
		
		// 첫 번째 구슬 굴림
		while(true) {
			r1 += dr[dir];
			c1 += dc[dir];
			if(map[r1][c1] != EMPTY) {
				if(map[r1][c1] == 'O') {
					goal++;
					break;
				}//if
				r1 -= dr[dir];
				c1 -= dc[dir];
				break;
			}
		}//while
		
		if(isEmpty(r1, c1)) map[r1][c1] = c;
		
		// 두 번째 구슬 굴림
		while(true) {
			r2 += dr[dir];
			c2 += dc[dir];
			if(map[r2][c2] != EMPTY) {
				if(map[r2][c2] == 'O') {
					goal++;
					break;
				}//if
				r2 -= dr[dir];
				c2 -= dc[dir];
				break;
			}//if
		}//while
		
		if(isEmpty(r1, c1)) map[r1][c1] = EMPTY;
		if(goal > 1) return null;

		if(!isRed) return new Position(r2, c2, r1, c1, cur.cnt+1);
		return new Position(r1, c1, r2, c2, cur.cnt+1);
	}//move

	// 이동한 칸이 구멍이 아닌 빈칸인지 확인
	private static boolean isEmpty(int r, int c) {
		return map[r][c] != 'O';
	}//isEmpty

	// 빨간 구슬 먼저 움직이는지 확인
	private static boolean redFirst(Position cur, int dir) {
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
	}//redFirst

	static class Position {
		int redR; // 빨간 구슬 행
		int redC; // 빨간 구슬 열
		int blueR; // 파란 구슬 행
		int blueC; // 파란 구슬 열
		int cnt; // 움직인 횟수
		public Position(int redR, int redC, int blueR, int blueC, int cnt) {
			super();
			this.redR = redR;
			this.redC = redC;
			this.blueR = blueR;
			this.blueC = blueC;
			this.cnt = cnt;
		}		
	}//Position

}//class