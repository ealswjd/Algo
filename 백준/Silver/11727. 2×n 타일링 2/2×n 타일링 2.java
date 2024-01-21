import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/11727
public class Main {
	static final int MOD = 10_007;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		br.close();

		dp[1] = 1;
		if(N>1) dp[2] = 3;
		
		for(int i=3; i<=N; i++) {
			dp[i] = (dp[i-1] + dp[i-2]* 2) % MOD;
		}//for
		
		System.out.print(dp[N] % MOD);
	}//main

}//class