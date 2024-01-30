import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2096
public class Main {
	static final int MIN=0, MAX=1;
	static int N;
	static int[][] map;
	static int[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][3];
		dp = new int[N][3][2];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		br.close();
		
		getMaxMin();		
		
		int min = Math.min(dp[N-1][0][MIN], Math.min(dp[N-1][1][MIN], dp[N-1][2][MIN]));
		int max = Math.max(dp[N-1][0][MAX], Math.max(dp[N-1][1][MAX], dp[N-1][2][MAX]));
		
		StringBuilder ans = new StringBuilder();
		ans.append(max).append(' ').append(min);
		System.out.print(ans);
	}//main

	private static void getMaxMin() {
		for(int i=0; i<3; i++) {
			dp[0][i][MIN] = map[0][i];
			dp[0][i][MAX] = map[0][i];
		}//for
		
		for(int i=1; i<N; i++) {
			// min
			dp[i][0][MIN] = Math.min(dp[i-1][0][MIN], dp[i-1][1][MIN]) + map[i][0];
			dp[i][1][MIN] = Math.min(dp[i-1][1][MIN], Math.min(dp[i-1][0][MIN], dp[i-1][2][MIN])) + map[i][1];
			dp[i][2][MIN] = Math.min(dp[i-1][2][MIN], dp[i-1][1][MIN]) + map[i][2];
			// max
			dp[i][0][MAX] = Math.max(dp[i-1][0][MAX], dp[i-1][1][MAX]) + map[i][0];
			dp[i][1][MAX] = Math.max(dp[i-1][1][MAX], Math.max(dp[i-1][0][MAX], dp[i-1][2][MAX])) + map[i][1];
			dp[i][2][MAX] = Math.max(dp[i-1][2][MAX], dp[i-1][1][MAX]) + map[i][2];
		}//for
		
	}//getMaxMin

}//class