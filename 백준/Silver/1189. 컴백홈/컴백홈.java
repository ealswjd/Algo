import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1189
public class Main {
	static int R, C, K, cnt;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		K = Integer.parseInt(st.nextToken()); // 거리
		
		map = new char[R][C];
		visited = new boolean[R][C];
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}//for i
		br.close();

		visited[R-1][0] = true; // 한수는 현재 왼쪽 아래점에 있고
		combackHome(R-1, 0, 1);
		
		System.out.print(cnt);
	}//main

	private static void combackHome(int r, int c, int len) {
		if(len == K && r == 0 && c == C-1) { // 집은 오른쪽 위에 있다.
			cnt++;
			return;
		}//if
		
		if(len > K) return;
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc) || visited[nr][nc] || map[nr][nc] == 'T') continue;
			visited[nr][nc] = true;
			combackHome(nr, nc, len+1);
			visited[nr][nc] = false;
		}//for
		
	}//combackHome

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}//rangeCheck

}//class