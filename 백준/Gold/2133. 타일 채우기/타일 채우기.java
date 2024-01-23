import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2133
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		br.close();

		dp[0] = 1;
		if(N > 1) dp[2] = 3;
		for(int i=4; i<=N; i+=2) {
			dp[i] = dp[i-2] * 3 + (dp[i-2] - dp[i-4]);
		}//for
		
		System.out.print(dp[N]);
	}//main

}//class