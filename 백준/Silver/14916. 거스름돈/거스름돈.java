import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/14916
public class Main {
	static final int INF = 1000000;
	static final int[] coin = {2, 5};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		br.close();
		
		int[] dp = new int[K+1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		for(int i=0; i<2; i++) {
			for(int n=coin[i]; n<=K; n++) {
				dp[n] = Math.min(dp[n], dp[n - coin[i]] + 1);
			}//for n
		}//for i
		
		if(dp[K] == INF) System.out.print(-1);
		else System.out.print(dp[K]);
	}//main

}//class