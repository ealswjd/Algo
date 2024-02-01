import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

// https://www.acmicpc.net/problem/1793
public class Main {
	static BigInteger[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = 250;
		dp = new BigInteger[N+1];
		
		dp[0] = new BigInteger("1");
		dp[1] = new BigInteger("1");
		dp[2] = new BigInteger("3");
		
		for(int i=3; i<=N; i++) {
			dp[i] = dp[i-1].add(dp[i-2].multiply(new BigInteger("2")));
		}//for
		
		String num;
		int n;
		StringBuilder ans = new StringBuilder();
		while((num = br.readLine()) != null && !num.equals("")) {
			n = Integer.parseInt(num);
			ans.append(dp[n]).append('\n');
		}//while

		System.out.print(ans);
	}//main

}//class