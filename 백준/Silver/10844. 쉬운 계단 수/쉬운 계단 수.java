import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/10844
public class Main {
	static final int MOD = 1_000_000_000;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[][] dp = new long[N+1][10];
		br.close();
		
		Arrays.fill(dp[1], 1);
		
		for(int i=2; i<=N; i++) {
			dp[i][0] = dp[i-1][1];
			for(int j=1; j<10; j++) {
				if(j == 9) dp[i][j] = dp[i-1][8] % MOD;
				else dp[i][j] = (dp[i-1][j-1]%MOD + dp[i-1][j+1]%MOD) % MOD;
				dp[i][j] %= MOD;
			}//for j
		}//for i

		long sum = 0;
		for(int i=1; i<10; i++) {
			sum += dp[N][i] % MOD;
		}
		
		System.out.println(sum % MOD);
	}//main

}//class