import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/1563
public class Main {
	static final int MOD = 1_000_000;
	static int N; // 한 학기는 N일
	static int[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		br.close();
		
		dp = new int[N+1][2][3];
		for(int i=0; i<=N; i++) {
			for(int j=0; j<2; j++) {
				Arrays.fill(dp[i][j], -1);
			}//for j
		}//for i
		
		dfs(0, 0, 0);
		System.out.println(dp[0][0][0]);
	}//main

	private static int dfs(int day, int L, int A) {
		if(L == 2 || A == 3) return 0;
		if(day == N) return 1;
		if(dp[day][L][A] != -1) return dp[day][L][A];
		
		dp[day][L][A] = 0;		
		dp[day][L][A] = dfs(day+1, L, 0) + dfs(day+1, L+1, 0) + dfs(day+1, L, A+1);		
		
		return dp[day][L][A] %= MOD;
	}//dfs

}//class