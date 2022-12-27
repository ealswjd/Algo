package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1189
public class Main_1189_컴백홈 {
	static int R, C, K, ans;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // 거리
		
		visited = new boolean[R][C];
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		br.close();
		
		visited[R-1][0] = true;
		comeBackHome(R-1, 0, 1); // 한수 현재 위치
		System.out.print(ans); // 거리가 K인 가짓수를 출력
	}//main

	private static void comeBackHome(int r, int c, int len) {
		if(len == K && r==0 && c==C-1) { // 거리가 K만큼 걸려서 집에 도착했으면
			ans++; // 가짓수 증가
			return;
		}
		if(len > K) return;
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(check(nr, nc)) continue;
			visited[nr][nc] = true;
			comeBackHome(nr, nc, len+1);
			visited[nr][nc] = false;
		}
		
	}//comeBackHome

	private static boolean check(int nr, int nc) {		
		return nr<0 || nr>=R || nc<0 || nc>=C || visited[nr][nc] || map[nr][nc]=='T';
	}//check

}//class

/*

3 4 6
....
.T..
....

-> 4

*/