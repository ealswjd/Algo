import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/14495
public class Main {
	static final int N = 116;
	static long[] dp;

	public static void main(String[] args) throws Exception {
		dp = new long[N+1];
		dp[1] = dp[2] = dp[3] = 1;
		for(int i=4; i<=N; i++) {
			dp[i] = dp[i-1] + dp[i-3];
		}//for

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.print(dp[n]);
	}//main

}//class