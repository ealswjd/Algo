import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/5582
public class Main {
	static char[] A, B;
	static int[][] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
		br.close();

		System.out.print(getCnt(A.length, B.length));
		System.out.println();
	}//main

	private static int getCnt(int a, int b) {
		int max = 0;
		dp = new int[a+1][b+1];
		
		for(int r=1; r<=a; r++) {
			for(int c=1; c<=b; c++) {
				// 두 문자가 같은 경우 이전 문자열들의 길이 + 1
				if(A[r-1] == B[c-1]) dp[r][c] = dp[r-1][c-1] + 1;
				max = Math.max(max, dp[r][c]);
			}//for c
		}//for r
		
		return max;
	}//getCnt

}//class