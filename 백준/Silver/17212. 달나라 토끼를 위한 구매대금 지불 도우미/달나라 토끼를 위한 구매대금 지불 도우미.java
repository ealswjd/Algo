import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/17212
public class Main {
	static final int N = 4;
	static final int[] coin = {1, 2, 5, 7};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int[] dp = new int[K+1];
		Arrays.fill(dp, 1000000);
		dp[0] = 0;
		
		for(int i=0; i<N; i++) {
			for(int n=coin[i]; n<=K; n++) {
				dp[n] = Math.min(dp[n], dp[n - coin[i]] + 1);
			}//for n
		}//for i

		System.out.print(dp[K]);
	}//main

}//class