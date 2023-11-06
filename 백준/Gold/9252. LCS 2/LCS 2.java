import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

// https://www.acmicpc.net/problem/9252
public class Main {
	static char[] A, B;
	static int max;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
		
		int lenA = A.length;
		int lenB = B.length;
		dp = new int[lenA+1][lenB+1];
		for(int i=1; i<=lenA; i++) Arrays.fill(dp[i], -1);		
		
		lcs(lenA, lenB);
		System.out.println(dp[lenA][lenB]);
		if(dp[lenA][lenB] > 0) printLCS(lenA, lenB);
	}//main

	private static void printLCS(int a, int b) {
		StringBuilder ans = new StringBuilder();
		Stack<Character> lcs = new Stack<>();
		
		while(a>0 && b>0) {
			if(dp[a][b] == dp[a-1][b]) a--;
			else if(dp[a][b] == dp[a][b-1]) b--;
			else {
				lcs.push(A[a-1]);
				a--;
				b--;
			}
		}//while
		
		while(!lcs.isEmpty()) {
			ans.append(lcs.pop());
		}//while
		
		System.out.print(ans);
	}//printLCS

	private static int lcs(int a, int b) {
		if(a==0 || b==0) return 0;
		if(dp[a][b] > -1) return dp[a][b];
		
		dp[a][b] = 0;
		if(A[a-1] == B[b-1]) dp[a][b] = lcs(a-1, b-1) + 1;
		else dp[a][b] = Math.max(lcs(a-1, b), lcs(a, b-1));
		
		return dp[a][b];
	}//lcs

}//class