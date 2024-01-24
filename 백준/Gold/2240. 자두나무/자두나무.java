import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2240
public class Main {
	static int T, W;
	static int[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		int num;
		dp = new int[3][T+1][W+2];
		
		for(int t=1; t<=T; t++) {
			num = Integer.parseInt(br.readLine());
			
			for(int w=1; w<=W+1; w++) {			
				switch (num) {
				case 1:
					dp[1][t][w] = Math.max(dp[1][t-1][w], dp[2][t-1][w-1]) + 1;
					dp[2][t][w] = Math.max(dp[2][t-1][w], dp[1][t-1][w-1]);
					break;
				case 2:
					if(t == 1 && w== 1) continue;
					dp[1][t][w] = Math.max(dp[1][t-1][w], dp[2][t-1][w-1]);
					dp[2][t][w] = Math.max(dp[2][t-1][w], dp[1][t-1][w-1]) + 1;			
					break;
				}//switch
			}//for

		}//for

		int max = 0;
		for(int w=1; w<=W+1; w++) {
			max = Math.max(dp[1][T][w], dp[2][T][w]);
		}
		System.out.print(max);
	}//main

}//class