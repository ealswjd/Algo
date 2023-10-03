import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17780
public class Main {
	// 				  색		     말	 화이트 레드 블루
	static final int COLOR=0, PIECE=1, W=0, R=1, B=2;
	static int N, K;
	static int[][][] map;
	static Piece[] pieces;
	static int[] dr = {0, 0, -1, 1}; // 우 좌 상 하
	static int[] dc = {1, -1, 0, 0}; // →, ←, ↑, ↓
	static int[] nd = {1, 0, 3, 2}; // 반대 방향
	static boolean end;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 체스판 크기
		K = Integer.parseInt(st.nextToken()); // 말의 개수
		map = new int[N][N][6]; // 체스판
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {				
				map[i][j][COLOR] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		
		pieces = new Piece[K+1];
		for(int i=1; i<=K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // 행
			int c = Integer.parseInt(st.nextToken()) - 1; // 열
			int d = Integer.parseInt(st.nextToken()) - 1; // 방향
			map[r][c][PIECE] = i;
			pieces[i] = new Piece(r, c, d);
		}//for
		br.close();

		end = false;
		int cnt = game();
		System.out.print(cnt);
	}//main
	
	private static int game() {
		int cnt = 0;
		int r, c, d;
		
		while(cnt++ <= 1000) {
			
			for(int i=1; i<=K; i++) {
				r = pieces[i].r;
				c = pieces[i].c;
				d = pieces[i].d;
				// 해당 말이 가장 아래에 있는 말이 아니면 
				if(map[r][c][PIECE] != i) continue;
				move(i, r, c, d);
				if(end) return cnt;
			}//for

		}//while
		
		return -1;
	}//game

	// 말 이동
	private static void move(int num, int r, int c, int d) {
		int nr = r + dr[d], nc = c + dc[d];
		int color, idx, len = getIdx(r, c);
		
		// 체스판을 벗어나거나 블루일 경우
		if(rangeCheck(nr, nc) || map[nr][nc][COLOR] == B) {
			d = nd[d];
			nr = r + dr[d];
			nc = c + dc[d];
			pieces[num].d = d;
			// 방향을 바꿔도 체스판을 벗어나거나 블루일 경우
			if(rangeCheck(nr, nc) || map[nr][nc][COLOR]==B) return;
		}//if
		
		color = map[nr][nc][COLOR]; // 체스판 색
		
		// 이동하려는 칸 색
		switch (color) {
		case W: // 화이트 : 순서대로
			idx = map[nr][nc][PIECE] == 0 ? PIECE : getIdx(nr, nc);
			for(int i=PIECE; i<len; i++) {
				if(idx >= 4) {
					end = true;
					return;
				}//if
				num = map[r][c][i];
				pieces[num].change(nr, nc);
				map[nr][nc][idx++] = num;
				map[r][c][i] = 0;
			}
			break;
		case R: // 레드 : 순서 바꿈
			idx = map[nr][nc][PIECE] == 0 ? PIECE : getIdx(nr, nc);
			for(int i=len-1; i>=PIECE; i--) {
				if(idx >= 4) {
					end = true;
					return;
				}//if
				num = map[r][c][i];
				pieces[num].change(nr, nc);
				map[nr][nc][idx++] = num;
				map[r][c][i] = 0;				
			}
			break;
		}//switch
			
	}//move

	private static int getIdx(int r, int c) {
		int idx = PIECE;
		while(map[r][c][idx] > 0) idx++;
		return idx;
	}//getIdx

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;
	}//rangeCheck

	// 체스 말
	static class Piece {
		int r; // 행
		int c; // 열
		int d; // 방향
		public Piece(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
		public void change(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}//Piece

}//class