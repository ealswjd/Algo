import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17837
public class Main {
	// 				  색		     말	     화이트 레드 블루
	static final int COLOR=0, PIECE=1, W=0, R=1, B=2;
	static int N, K;
	static int[][][] map;
	static Piece[] pieces;
	static int[] dr = {0, 0, -1, 1}; // 우 좌 상 하
	static int[] dc = {1, -1, 0, 0}; // →, ←, ↑, ↓
	static int[] nd = {1, 0, 3, 2}; // 반대 방향

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 체스판 크기
		K = Integer.parseInt(st.nextToken()); // 말의 개수
		map = new int[N][N][5]; // 체스판
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

		int cnt = game();
		System.out.print(cnt);
	}//main
	
	private static int game() {
		int cnt = 0;
		int r, c, d, pos;
		
		while(cnt++ <= 1000) {			
			for(int i=1; i<=K; i++) {
				r = pieces[i].r;
				c = pieces[i].c;
				d = pieces[i].d;
				pos = PIECE;
				// 해당 말이 가장 아래에 있는 말이 아니면 
				if(map[r][c][PIECE] != i) {
					while(map[r][c][pos] != i) pos++;
				}
				if(move(pos, i, r, c, d)) return cnt;
			}//for
		}//while
		
		return -1;
	}//game

	// 말 이동 
	private static boolean move(int pos, int num, int r, int c, int d) {
		int nr = r + dr[d], nc = c + dc[d];
		int color, idx, len = getIdx(pos, r, c); // 현재 위치 말 개수
		
		// 체스판을 벗어나거나 블루일 경우
		if(rangeCheck(nr, nc) || map[nr][nc][COLOR] == B) {
			d = nd[d]; // 반대 방향
			nr = r + dr[d]; // 다음 행
			nc = c + dc[d]; // 다음 열
			pieces[num].d = d; // 방향 변경
			// 방향을 바꿔도 체스판을 벗어나거나 블루일 경우
			if(rangeCheck(nr, nc) || map[nr][nc][COLOR]==B) return false;
		}//if
		
		color = map[nr][nc][COLOR]; // 체스판 색
		idx = map[nr][nc][PIECE] == 0 ? PIECE : getIdx(PIECE, nr, nc); // 다음 위치 말 개수
		
		// 이동하려는 칸 색
		switch (color) {
		case W: // 화이트 : 순서대로
			for(int i=pos; i<len; i++) {
				if(idx >= 4) return true;			
				change(map[r][c][i], i, idx++, r, c, nr, nc);
			}//for
			break;
		case R: // 레드 : 순서 바꿈
			for(int i=len-1; i>=pos; i--) {
				if(idx >= 4) return true;
				change(map[r][c][i], i, idx++, r, c, nr, nc);			
			}//for
			break;
		}//switch
		
		return false;
	}//move

	// 말 상태 변경
	private static void change(int num, int i, int idx, int r, int c, int nr, int nc) {
		pieces[num].change(nr, nc); // 말의 행 열 변경
		map[nr][nc][idx++] = num; // 말 이동
		map[r][c][i] = 0; // 기존 위치 비우기
	}//change 

	// 인덱스 구하기
	private static int getIdx(int pos, int r, int c) {
		int idx = pos; // 말 기본 위치
		while(map[r][c][idx] > 0) idx++; // 빈자리일 때까지 인덱스 증가
		return idx; // 빈 인덱스 반환
	}//getIdx

	// 범위 체크
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