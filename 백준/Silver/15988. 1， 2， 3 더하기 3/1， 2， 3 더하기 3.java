import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/15988
public class Main {
	static final int MOD = 1_000_000_009;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int len = 1_000_001;
		long[] dp = new long[len];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int i=4; i<len; i++) {
			dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % MOD;
		}//for
		
		int N;
		StringBuilder ans = new StringBuilder();
		while(T-->0) {
			N = Integer.parseInt(br.readLine());
			ans.append(dp[N]).append('\n');
		}//while

		System.out.print(ans);
	}//main

}//class