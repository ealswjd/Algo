import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7579
public class Main {
	static final int MEMORY = 10000000;
	static int N, M;
	static int[][] apps;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 앱 개수
		M = Integer.parseInt(st.nextToken()); // 필요한 메모리
		apps = new int[N][2];
				
		for(int i=0; i<2; i++) {
			input(i, new StringTokenizer(br.readLine()));
		}//for
		br.close();
		
		dp = new int[MEMORY+1];
		Arrays.fill(dp, -1);
		
		System.out.print(getMinCost());
	}//main

	private static int getMinCost() {
		
		for(int a=0; a<N; a++) {
			int cost = apps[a][1];
			for(int c=MEMORY; c>=cost; c--) {
				if(dp[c-cost] == -1) continue;
				if(dp[c-cost] + apps[a][0] > dp[c]) {
					dp[c] = dp[c-cost] + apps[a][0];
				}
			}//for c
			if(dp[cost] < apps[a][0]) dp[cost] = apps[a][0];
		}//for a

		for(int cost=0; cost<=MEMORY; cost++) {
			if(dp[cost] >= M) {
				return cost;
			}//if
		}//for
		
		return 0;
	}//getMinCost

	private static void input(int idx, StringTokenizer st) {
		for(int i=0; i<N; i++) {
			apps[i][idx] = Integer.parseInt(st.nextToken());
		}//for		
	}//input

}//class