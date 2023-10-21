import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder ans = new StringBuilder();
		while(T-->0) {
			int N = Integer.parseInt(br.readLine());
			ans.append(getCnt(N)).append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static int getCnt(int N) {
		dp = new int[N+1];
		dp[0] = 1;
		
		for(int num=1; num<=3; num++) {
			for(int i=num; i<=N; i++) {
				dp[i] += dp[i-num];
			}//for i
		}//for num
		
		return dp[N];
	}//getCnt

}//class