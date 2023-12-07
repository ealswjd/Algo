import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2079
public class Main {
	static char[] str;
	static int[] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toCharArray();
		br.close();

		int len = str.length;
		dp = new int[len+1];
		Arrays.fill(dp, 2000);
		dp[0] = 0;
		for(int e=0; e<len; e++) {
			for(int s=0; s<=e; s++) {
				if(isPalindrome(s, e)) dp[e+1] = Math.min(dp[e+1], dp[s] + 1);
				else dp[e+1] = Math.min(dp[e+1], dp[e] + 1);
			}//for j
		}//for i
		
		System.out.print(dp[len]);
	}//main

	private static boolean isPalindrome(int s, int e) {
		if(s>=e) return true;
		
		if(str[s] == str[e]) return isPalindrome(s+1, e-1);		
		return false;
	}//isPalindrome

}//class