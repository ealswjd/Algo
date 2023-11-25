import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9711
public class Main {
	static BigInteger[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int len = 10_000;
		dp = new BigInteger[len+1];
		dp[0] = BigInteger.valueOf(0);
        dp[1] = BigInteger.valueOf(1);
        
		for(int i=2; i<=len; i++) {
			dp[i] = dp[i-1].add(dp[i-2]);
		}//for
		
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		int t = 1;
		int P, Q;
		while(T-->0) {
			ans.append("Case #").append(t++).append(": ");
			st = new StringTokenizer(br.readLine());
			P = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			ans.append(dp[P].remainder(BigInteger.valueOf(Q))).append("\n");
		}//while

		System.out.print(ans);
	}//main

}//class