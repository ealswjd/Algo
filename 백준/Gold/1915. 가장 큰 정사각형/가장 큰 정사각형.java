import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1915
public class Main {
	static int N, M;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N+1][M+1];
		int max = 0;
		for(int i=1; i<=N; i++) {
			char[] map = br.readLine().toCharArray();
			for(int j=1; j<=M; j++) {
				if(map[j-1] == '0') continue;
				dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
				max = Math.max(max, dp[i][j]);
			}//for j
		}//for i
		br.close();
		
		max *= max;
		System.out.print(max);
	}//main

}//class