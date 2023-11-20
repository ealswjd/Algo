import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/15482
public class Main {
	static char[] A, B;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
		br.close();

		System.out.print(LCS(A.length, B.length));
	}//main	

	private static int LCS(int a, int b) {
		dp = new int[a+1][b+1];
		
		for(int r=1; r<=a; r++) {
			for(int c=1; c<=b; c++) {
				if(A[r-1] == B[c-1]) dp[r][c] = dp[r-1][c-1] + 1;
				else dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]);
			}//for c
		}//for r
		
		return dp[a][b];
	}//LCS

}//class