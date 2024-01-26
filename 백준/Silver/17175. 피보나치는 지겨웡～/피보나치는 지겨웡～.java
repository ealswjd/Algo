import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/17175
public class Main {
	static final int MOD = 1_000_000_007;
	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		
		dp[0] = 1;
		if(N>0) dp[1] = 1;
		for(int i=2; i<=N; i++) {
			dp[i] = (dp[i-2] + dp[i-1] + 1) % MOD;
		}//for
		
		System.out.print(dp[N] % MOD);
	}//main

}//class