import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11048
public class Main {
	static int N, M;
	static int[][] map, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}//for j
		}//for i
		br.close();
		
		getMax(0, 0, 0);
		System.out.print(dp[0][0]);
	}//main

	private static int getMax(int r, int c, int sum) {
		if(rangeCheck(r, c)) return sum;
		else {
			if(dp[r][c] != -1) return dp[r][c];
		}
		
		if(r == N && c == M) return sum;
		dp[r][c] = Math.max(getMax(r+1, c, sum), Math.max(getMax(r, c+1, sum), getMax(r+1, c+1, sum))) + map[r][c];
		return dp[r][c];
	}//getMax

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}//rangeCheck

}//class