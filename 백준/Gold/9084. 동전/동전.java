import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9084
public class Main {
	static int N, M;
	static int[] coins;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		while(T-->0) {
			N = Integer.parseInt(br.readLine()); // 동전 가지 수
			coins = new int[N]; // 동전
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}//for
			
			M = Integer.parseInt(br.readLine()); // 목표 금액						
			ans.append(getCnt()).append("\n");
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

	private static int getCnt() {
		int[] dp = new int[M+1]; // 목표 금액을 만들 수 있는 경우의 수
		dp[0] = 1;
		
		for(int coin : coins) {
			for(int i=coin; i<=M; i++) {
				dp[i] += dp[i-coin];
			}//for
		}//coin
		
		return dp[M];
	}//getCnt

}//class