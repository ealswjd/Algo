import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3187
public class Main {
	static int R, C, v, k;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		
		int vCnt = 0, kCnt = 0;
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'v') vCnt++;
				else if(map[i][j] == 'k') kCnt++;
			}//for j
		}//for i
		br.close();
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(visited[i][j] || map[i][j] == '.' || map[i][j] == '#') continue;
				v = k = 0;
				visited[i][j] = true;
				dfs(i, j);
				if(k > v) vCnt -= v;
				else kCnt -= k;
			}//for j
		}//for i

		StringBuilder ans = new StringBuilder();
		ans.append(kCnt).append(' ').append(vCnt);
		System.out.print(ans);
	}//main

	private static void dfs(int r, int c) {
		if(map[r][c] == 'v') v++;
		else if(map[r][c] == 'k') k++;
		
		int nr, nc;
		for(int i=0; i<4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
			visited[nr][nc] = true;
			dfs(nr, nc);
		}//for
		
	}//dfs

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C || map[r][c] == '#';
	}//rangeCheck

}//class