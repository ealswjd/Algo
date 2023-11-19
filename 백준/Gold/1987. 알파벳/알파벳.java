import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1987
public class Main {
	static int R, C, max;
	static boolean[] visited;
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		
		map = new char[R][C]; // 지도
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}//for
		br.close();
		
		visited = new boolean[26]; // 알파벳 사용
		visited[map[0][0]-'A'] = true; // 좌측 상단 알파벳 사용 처리
		getCnt(0, 0, 1);
		System.out.print(max);
	}//main

	private static void getCnt(int r, int c, int cnt) {
		int nr, nc;
		max = Math.max(max, cnt); // 최대 개수 갱신
		
		for(int i=0; i<4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			
			if(rangeCheck(nr, nc)) continue; // 범위 벗어남
			if(visited[map[nr][nc]-'A']) continue;
			
			visited[map[nr][nc]-'A'] = true; // 알파벳 사용 처리
			getCnt(nr, nc, cnt+1);
			visited[map[nr][nc]-'A'] = false; // 알파벳 사용 X
		}//for
		
	}//getCnt

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=R || c<0 || c>=C;
	}//rangeCheck

}//class