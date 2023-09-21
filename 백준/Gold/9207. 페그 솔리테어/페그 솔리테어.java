import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9207
public class Main {
	static final int R=5, C=9;
	static final char PIN='o', EMPTY='.';
	static char[][] map; // 게임판
	static int minPinCnt, minMoveCnt, total; // 핀의 최소 개수, 최소 이동 횟수, 총 개수
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 케이스의 개수
		StringBuilder ans = new StringBuilder();
		
		while(T-->0) {
			map = new char[R][C]; // 게임판
			total = 0;
			for(int i=0; i<R; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j=0; j<C; j++) {
					if(map[i][j] == PIN) total++;
				}//for j
			}//for i
			
			minPinCnt = total; // 핀의 최소 개수
			minMoveCnt = 0; // 최소 이동 횟수
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(map[i][j] == PIN) move(i, j, total, 0);					
				}//for j
			}//for i
			
			ans.append(minPinCnt).append(" ");
			ans.append(minMoveCnt).append("\n");
			if(T > 0) br.readLine();
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

	private static void move(int r, int c, int pinCnt, int moveCnt) {
		if(pinCnt <= minPinCnt) {
			minPinCnt = pinCnt;
			minMoveCnt = moveCnt;			
		}//if
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc) || map[nr][nc] != PIN || pinCnt - 1 < 1) continue;
			if(rangeCheck(nr+dr[i], nc+dc[i]) || map[nr+dr[i]][nc+dc[i]] != EMPTY) continue;
			
			map[r][c] = map[nr][nc] = EMPTY;
			map[nr+dr[i]][nc+dc[i]] = PIN;

			for(int n=0; n<R; n++) {
				for(int m=0; m<C; m++) {
					if(map[n][m] == PIN) move(n, m, pinCnt-1, moveCnt+1);					
				}//for j
			}//for i

			map[r][c] = map[nr][nc] = PIN;
			map[nr+dr[i]][nc+dc[i]] = EMPTY;
		}//for

	}//move

	// 범위 체크
	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}//rangeCheck

}//class