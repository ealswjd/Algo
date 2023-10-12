import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14712
public class Main {
	static int N, M, result;
	static int[][] map;
	static int[] dr = {-1, -1, 0};
	static int[] dc = {-1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		br.close();		

		result = 0;
		map = new int[N][M];

		getCnt(0, N*M);			

		System.out.print(result);
	}//main

	private static void getCnt(int cnt, int max) {
		if(cnt == max) {
			result++;
			return;
		}//if
		
		int r = cnt / M;
		int c = cnt % M;
		
		getCnt(cnt+1, max); // 넴모 안 채우고
		
		if(!check(r, c)) { // 4 넴모 아니면
			map[r][c] = 1; // 넴모 채우기
			getCnt(cnt+1, max);
			map[r][c] = 0; // 넴모 제거
		}//if
		
	}//getCnt

	private static boolean check(int r, int c) {
		int cnt = 0;
		for(int i=0; i<3; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc)) continue;
			if(map[nr][nc] == 1) cnt++;
		}//for
		return cnt == 3;
	}//check

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || c < 0;
	}//rangeCheck

}//class