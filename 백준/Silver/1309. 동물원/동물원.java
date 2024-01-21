import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1309
public class Main {
	static final int MOD = 9901;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		br.close();
		
		dp[1] = 3;
		if(N > 1) dp[2] = 7;

		for(int i=3; i<=N; i++) {
			dp[i] = (dp[i-2] + dp[i-1] * 2) % MOD;
		}//for
		
		System.out.print(dp[N] % MOD);
	}//main

}//class