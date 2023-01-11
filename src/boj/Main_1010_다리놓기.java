package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_다리놓기 {
	static int N;
	static int M;
	static int[][] dp = new int[30][30];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테케 수
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 서쪽 사이트 개수
			M = Integer.parseInt(st.nextToken()); // 동쪽 사이트 개수
			sb.append(comb(M, N)).append("\n");
		}
		br.close();
		System.out.println(sb);

	}//main
	
	/*
	 		**조합성질**
	(n+1)C(r+1) = nCr + nC(r+1)
	nC0 = nCn = 1
	 */
	private static int comb(int n, int r) {
		if(dp[n][r] > 0) {
			return dp[n][r];
		}		
		if(n == r || r == 0) {
			return dp[n][r] = 1;
		}
		
		return dp[n][r] = comb(n-1, r-1) + comb(n-1, r);		
	}

}//class
