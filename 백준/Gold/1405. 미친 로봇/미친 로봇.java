import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1405
public class Main {
	static final int M = 30;
	static int N;
	static double ans;
	static double[] percentages;
	static boolean[][] visited;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		percentages = new double[4]; // 동서남북
		for(int i=0; i<4; i++) {
			percentages[i] = Double.parseDouble(st.nextToken()) * 0.01;
		}//for
		br.close();

		visited = new boolean[M][M];
		visited[15][15] = true;
		move(15, 15, 0, 1.0);
		
		System.out.print(ans);
	}//main

	private static void move(int r, int c, int cnt, double sum) {
		if(cnt == N) {
			ans += sum;
			return;
		}//if
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc)) continue;
			visited[nr][nc] = true;
			move(nr, nc, cnt+1, sum * percentages[i]);
			visited[nr][nc] = false;
		}
	}//move

	private static boolean rangeCheck(int r, int c) {
		return r<=0 || r>=M || c<=0 || c>=M || visited[r][c];
	}//rangeCheck

}//class