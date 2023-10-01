import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16197
public class Main {
	static final char WALL='#';
	static int N, M, minCnt;
	static char[][] map;
	static int[] dr= {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		map = new char[N][M];
		
		int[] coin = new int[4];
		for(int r=0, i=0; r<N; r++) {
			map[r] = br.readLine().toCharArray();
			for(int c=0; c<M; c++) {
				if(map[r][c] == 'o') { // 동전
					coin[i++] = r;
					coin[i++] = c;
				}//if
			}//for c
		}//for r
		br.close();
		
		minCnt = 11;
		getMinCnt(coin, 0);
		
		if(minCnt == 11) System.out.print(-1);
		else System.out.print(minCnt);
	}//main


	private static void getMinCnt(int[] coin, int cnt) {
		if(cnt >= minCnt || cnt > 10) return;
		
		int r1 = coin[0], c1 = coin[1];
		int r2 = coin[2], c2 = coin[3];
		boolean out1, out2;
		
		for(int i=0; i<4; i++) {
			int nr1 = r1 + dr[i];
			int nc1 = c1 + dc[i];
			int nr2 = r2 + dr[i];
			int nc2 = c2 + dc[i];
			
			out1 = rangeCheck(nr1, nc1);
			out2 = rangeCheck(nr2, nc2);
			if(out1 || out2) {
				if(out1 == out2) continue;	
				minCnt = Math.min(minCnt, cnt+1);
				return;								
			}//if
			
			if(map[nr1][nc1] == WALL) {
				nr1 = r1;
				nc1 = c1;
			}//if
			if(map[nr2][nc2] == WALL) {
				nr2 = r2;
				nc2 = c2;
			}//if
			
			getMinCnt(new int[] {nr1, nc1, nr2, nc2}, cnt+1);
		}//for
		
	}//getMinCnt

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}//rangeCheck	

}//class