import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, cnt;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 1, 0);
		
		System.out.print(cnt);

	}//main

	private static void dfs(int x, int y, int d) {
		if(x == N-1 && y == N-1) {
			cnt++;
			return;
		}
		
		switch (d) {
		case 0: // 가로
			if(y+1 < N && map[x][y+1] == 0) dfs(x, y+1, 0);
			break;
		case 1: // 세로
			if(x+1 < N && map[x+1][y] == 0) dfs(x+1, y, 1);			
			break;
		case 2: // 대각선
			if(y+1 < N && map[x][y+1] == 0) dfs(x, y+1, 0);
			if(x+1 < N && map[x+1][y] == 0) dfs(x+1, y, 1);						
			break;
		}
		
		if(x+1 < N && y+1 < N && map[x][y+1] == 0 && map[x+1][y] == 0 && map[x+1][y+1] == 0) {
			dfs(x+1, y+1, 2);
		}
		
	}//dfs

}//class