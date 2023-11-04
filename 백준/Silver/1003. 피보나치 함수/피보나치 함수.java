import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1003
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[41][2]; // N은 40보다 작거나 같은 자연수 또는 0
		dp[0][0] = 1;
		dp[1][1] = 1;
		for(int i=2; i<41; i++) {
			dp[i][0] = dp[i-1][0] + dp[i-2][0]; // 0의 개수
			dp[i][1] = dp[i-1][1] + dp[i-2][1]; // 1의 개수
		}//for
		
		StringBuilder ans = new StringBuilder();
		int N;
		while(T-->0) {
			N = Integer.parseInt(br.readLine());
			ans.append(dp[N][0]).append(" "); // 0이 출력되는 횟수
			ans.append(dp[N][1]).append("\n"); // 1이 출력되는 횟수
		}//while

		System.out.print(ans);
	}//main

}//class