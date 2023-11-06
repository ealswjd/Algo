import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/9251
public class Main {
	static char[] A, B;
	static int[][] dp;
	static int max;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
		br.close();
		
		int lenA = A.length;
		int lenB = B.length;
		dp = new int[lenA][lenB];
		for(int i=0; i<lenA; i++) {
			Arrays.fill(dp[i], -1);
		}//for

		System.out.println(lcs(lenA-1, lenB-1));
	}//main

	private static int lcs(int a, int b) {
		if(a<0||b<0) return 0;
		
		if(dp[a][b] != -1) return dp[a][b];
		
		dp[a][b]=0;
		if(A[a] == B[b]) dp[a][b] = lcs(a-1, b-1)+1;
		else dp[a][b] = Math.max(lcs(a-1,b), lcs(a,b-1));
		
		return dp[a][b];
	}//lcs

}//class