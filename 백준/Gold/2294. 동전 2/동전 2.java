import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2294
public class Main {
	static final int INF=987654321;
	static int N, K;
	static int[] coin, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coin = new int[N];
		dp = new int[K+1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		for(int i=0; i<N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}//for
		br.close();	
		
		for(int i=0; i<N; i++) {
			for(int n=coin[i]; n<=K; n++) {
				dp[n] = Math.min(dp[n], dp[n - coin[i]] + 1);
			}//for n
		}//for i
		
		if(dp[K] == INF) System.out.print(-1);
		else System.out.print(dp[K]);
	}//main

}//class