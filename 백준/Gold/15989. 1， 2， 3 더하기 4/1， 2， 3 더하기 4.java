import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		getCnt();
		
		StringBuilder ans = new StringBuilder();
		while(T-->0) {
			int N = Integer.parseInt(br.readLine());
			ans.append(dp[3][N]).append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static void getCnt() {
		dp = new int[4][10001];
		dp[0][0] = 1;
		
		for(int num=1; num<=3; num++) {
			for(int i=1; i<=10000; i++) {
				if(num==i) dp[num][i] = Math.max(dp[num-1][i], dp[num][i-1]) + 1;
				else if(num>i) dp[num][i] = dp[num-1][i];
				else dp[num][i] += dp[num-1][i] + dp[num][i-num];				
			}//for i
		}//for num

	}//getCnt

}//class