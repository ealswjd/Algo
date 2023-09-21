import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// https://www.acmicpc.net/problem/9207
public class Main {
	static final int R=5, C=9;
	static final char PIN='o', EMPTY='.';
	static int[][] map; // 게임판
	static int minPinCnt, minMoveCnt, total; // 핀의 최소 개수, 최소 이동 횟수
	static ArrayList<int[]> pinList; // 핀
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 케이스의 개수
		StringBuilder ans = new StringBuilder();
		
		while(T-->0) {
			map = new int[R][C]; // 게임판
			pinList = new ArrayList<>();
			total = 0;
			for(int i=0; i<R; i++) {
				char[] tmp = br.readLine().toCharArray();
				for(int j=0; j<C; j++) {
					map[i][j] = tmp[j];
					if(map[i][j] == PIN) {
						pinList.add(new int[] {i, j, 1});
						map[i][j] = total++;
					}
				}//for j
			}//for i
			
			minPinCnt = total; // 핀의 최소 개수
			minMoveCnt = 0; // 최소 이동 횟수
			
			for(int i=0; i<total; i++) {
				move(i, total, 0);
			}//for
			
			ans.append(minPinCnt).append(" ");
			ans.append(minMoveCnt).append("\n");
			if(T > 0) br.readLine();
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

	private static void move(int start, int pinCnt, int moveCnt) {
		if(pinCnt <= minPinCnt) {
			minPinCnt = pinCnt;
			minMoveCnt = moveCnt;			
		}//if	
		
		int[] cur = pinList.get(start);
		int r = cur[0];
		int c = cur[1];
		int status = cur[2];
		if(status == 0) return;
		int num = map[r][c];
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc) || !(0<=map[nr][nc] && map[nr][nc]<8) || pinCnt - 1 < 1) continue;
			int nnr = nr+dr[i];
			int nnc = nc+dc[i];
			if(rangeCheck(nnr, nnc) || map[nnr][nnc] != EMPTY) continue;
			int next = map[nr][nc];

			map[r][c] = map[nr][nc] = EMPTY;
			map[nnr][nnc] = num;
			pinList.get(start)[0] = nnr;
			pinList.get(start)[1] = nnc;		
			pinList.get(next)[2] = 0;
			
			for(int n=0; n<total; n++) {
				if(pinList.get(n)[2] == 0) continue;
				move(n, pinCnt-1, moveCnt+1);
			}//for
			
			map[r][c] = num;
			map[nr][nc] = next;
			map[nnr][nnc] = EMPTY;
			pinList.get(start)[0] = r;
			pinList.get(start)[1] = c;		
			pinList.get(next)[2] = 1;

		}//for

	}//move

	// 범위 체크
	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}//rangeCheck

}//class