import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

// https://www.acmicpc.net/problem/10826
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();
		
		if(N==0) System.out.print(0);
		else if(N==1) System.out.print(1);
		else {
			BigInteger[] dp = new BigInteger[N+1];
			dp[0] = BigInteger.ZERO;
			dp[1] = BigInteger.ONE;
			for(int n=2; n<=N; n++) {
				dp[n] = dp[n-1].add(dp[n-2]);
			}//for
			
			System.out.print(dp[N]);			
		}//else
		
	}//main

}//class