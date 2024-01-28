import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2688
public class Main {
	static final int N = 64;
	static long[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init();		
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder ans = new StringBuilder();
		int n;
		while(T-->0) {
			n = Integer.parseInt(br.readLine());
			ans.append(dp[n][9]).append('\n');
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static void init() {
		dp = new long[N+1][10];
		for(int i=0; i<10; i++) {
			dp[1][i] = i+1;
		}//for
		
		for(int i=2; i<=N; i++) {
			dp[i][0] = 1;
			for(int j=1; j<10; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}//for j
		}//for i
		
	}//init

}//class