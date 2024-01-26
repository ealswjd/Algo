import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/8394
public class Main {
	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		br.close();
		
		dp[1] = 1;
		if(N > 1) dp[2] = 2;
		for(int i=3; i<=N; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10;
		}//for

		System.out.print(dp[N]);
	}//main

}//class