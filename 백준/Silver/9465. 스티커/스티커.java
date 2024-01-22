import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9465
public class Main {
	static int N;
	static int[][] sticker;
	static int[][] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int max;
		while(T-->0) {
			N = Integer.parseInt(br.readLine());
			dp = new int[2][N+1];
			sticker = new int[2][N];
			
			for(int i=0; i<2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N;  j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
					dp[i][j+1] = sticker[i][j];
				}//for j
			}//for i
			
			max = Math.max(dp[0][1], dp[1][1]);
			for(int i=2; i<=N; i++) {
				dp[0][i] = Math.max(dp[1][i-1] + sticker[0][i-1], Math.max(dp[0][i-2] + sticker[0][i-1], dp[0][i-1]));
				dp[1][i] = Math.max(dp[0][i-1] + sticker[1][i-1], Math.max(dp[1][i-2] + sticker[1][i-1], dp[1][i-1]));
				max = Math.max(max, Math.max(dp[0][i], dp[1][i]));
			}//for
			ans.append(max).append('\n');
		}//while

		System.out.print(ans);
	}//main

}//class