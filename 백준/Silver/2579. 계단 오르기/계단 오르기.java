import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2579
public class Main {
	static int N;
	static int[] number, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		number = new int[N+1];
		dp = new int[N+1];
		for(int i=1; i<=N; i++) {
			number[i] = Integer.parseInt(br.readLine());
		}//for
		br.close();
		
		Arrays.fill(dp, -1);
		dp[0] = 0;
		dp[1] = number[1];
		if(N > 1) {
			dp[2] = number[1] + number[2];
		}
		int max = getMax(N);
		System.out.print(max);
	}//main

	private static int getMax(int n) {
		
		if(dp[n] == -1) {
			dp[n] = Math.max(getMax(n-2), getMax(n-3) + number[n-1]) + number[n];
		}//if
		
		return dp[n];
	}//getMax

}//class