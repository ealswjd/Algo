import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/15990
public class Main {
	static final int MOD = 1_000_000_009;
	static long[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());		
		getCnt();
		
		StringBuilder ans = new StringBuilder();
		int N;
		while(T-->0) {
			N = Integer.parseInt(br.readLine());
			ans.append((dp[1][N] + dp[2][N] + dp[3][N]) % MOD).append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static void getCnt() {
		int size = 100_001;
		dp = new long[4][size];
		dp[1][1] = 1;
		dp[2][2] = 1;
		dp[1][3] = 1;
		dp[2][3] = 1;
		dp[3][3] = 1;

		for(int n=4; n<size; n++) {
			dp[1][n] = (dp[2][n-1] + dp[3][n-1]) % MOD;
			dp[2][n] = (dp[1][n-2] + dp[3][n-2]) % MOD;
			dp[3][n] = (dp[1][n-3] + dp[2][n-3]) % MOD;
		}//for
		
	}//getCnt

}//class