import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/26169
public class Main {
	static final int SIZE=5;
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	static int[][] map;
	static boolean flag;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[SIZE][SIZE];
		
		StringTokenizer st;
		for(int i=0; i<SIZE; i++) {
			st  = new StringTokenizer(br.readLine());
			for(int j=0; j<SIZE; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		br.close();
		
		dfs(r, c, 0, 0);
		System.out.print(flag ? 1 : 0);
	}//main

	private static void dfs(int r, int c, int moveCnt, int appleCnt) {		
		if(flag) return;
		if(map[r][c] == 1) appleCnt++;
		if(moveCnt == 3) {
			if(appleCnt >= 2) flag = true;
			return;
		}//if
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc)) continue;
			
			map[r][c] = -1;
			dfs(nr, nc, moveCnt+1, appleCnt);				
			map[r][c] = 0;
		}//for
		
	}//dfs	

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=SIZE || c<0 || c>=SIZE || map[r][c]==-1;
	}//rangeCheck

}//class