import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1463
public class Main {
	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		br.close();

		for(int i=2; i<=N; i++) {
			dp[i] = dp[i-1] + 1;
			if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2] + 1);
			if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3] + 1);
		}//for		
		
		System.out.print(dp[N]);
	}//main

}//class