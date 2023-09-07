import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1520
public class Main {
	static int R, C; // 행, 열
	static int[][] map; // 지도
	static int[][] cnt; // 경로의 수
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열		
		init();
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		br.close();
		
		int H = getCnt(0, 0);
		System.out.print(H);
	}//main

	private static int getCnt(int r, int c) {
		if(cnt[r][c] != -1) return cnt[r][c];
		if(r == R-1 && c == C-1) return 1;
		
		cnt[r][c] = 0;
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc) || map[nr][nc] >= map[r][c]) continue;					

			cnt[r][c] += getCnt(nr, nc);				
		}//for
		
		return cnt[r][c];
	}//getCnt

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}//rangeCheck

	private static void init() {
		map = new int[R][C]; // 지도
		cnt = new int[R][C]; // 경로의 수
		for(int i=0; i<R; i++) {
			Arrays.fill(cnt[i], -1);
		}//for
	}//init

}//class