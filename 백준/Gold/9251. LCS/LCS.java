import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9251
public class Main {
	static char[] A, B;
	static int[][] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
		br.close();
		
		int lenA = A.length;
		int lenB = B.length;

		dp = new int[lenA+1][lenB+1];
		System.out.print(lcs(lenA, lenB));
	}//main

	private static int lcs(int a, int b) {
		for(int r=1; r<=a; r++) {
			for(int c=1; c<=b; c++) {
				if(A[r-1] == B[c-1]) dp[r][c] = dp[r-1][c-1] + 1;
				else dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]);
			}//for c
		}//for r
		
		return dp[a][b];
	}//lcs

}//class