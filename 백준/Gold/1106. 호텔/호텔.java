import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1106
public class Main {
	static int C, N;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken()); // 만족해야 하는 최소 고객 수
		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		
		dp = new int[C+101]; // 최소 C명
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		int max = C+100;
		int prev;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=cnt; j<=max; j++) {
				prev = dp[j - cnt];
				if(prev != Integer.MAX_VALUE) dp[j] = Math.min(dp[j], prev+cost);
			}//if
		}//for
		br.close();
		
		int min = Integer.MAX_VALUE;
		for(int i=C; i<=max; i++) {
			min = Math.min(min, dp[i]);
		}//for
		
		System.out.print(min);
	}//main

}//class