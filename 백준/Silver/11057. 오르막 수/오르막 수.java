import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/11057
public class Main {
	static final int MOD = 10_007;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][10];
		br.close();
		
		for(int i=0; i<10; i++) {
			dp[1][i] = 1; // 길이가 1일 때 오르막 수 개수
		}//for
		
		for(int i=2; i<=N; i++) {
			for(int j=0; j<10; j++) {
				for(int k=0; k<=j; k++) {
					dp[i][j] += dp[i-1][k];
					dp[i][j] %= MOD;
				}//for k
			}//for j
		}//for i

		int sum = 0;
		for(int i=0; i<10; i++) {
			sum += dp[N][i];
		}//for
		
		System.out.print(sum % MOD);
	}//main

}//class