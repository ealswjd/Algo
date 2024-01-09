import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2775
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] dp = new int[15][15];
		for(int i=1; i<15; i++) {
			dp[0][i] = i;
		}//for
		
		for(int k=1; k<15; k++) {
			for(int n=1; n<15; n++) {
				dp[k][n] = dp[k-1][n] + dp[k][n-1];
			}//for n
		}//for k
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder(); 
		int k, n;
		while(T-->0) {
			k = Integer.parseInt(br.readLine());
			n = Integer.parseInt(br.readLine());
			ans.append(dp[k][n]).append('\n');
		}//while

		System.out.print(ans);
	}//main

}//class