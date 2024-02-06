import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2193
public class Main {
	static int N;
	static long[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new long[N+1][2];
		
		dp[1][0] = 0;
		dp[1][1] = 1;
		if(N > 1) {
			dp[2][0] = 1;
			dp[2][1] = 0;
		}
		for(int i=3; i<=N; i++) {
			dp[i][0] = dp[i-1][0] + dp[i-1][1];
			dp[i][1] = dp[i-1][0];
		}//for

		System.out.print(dp[N][0] + dp[N][1]);
	}//main

}//class