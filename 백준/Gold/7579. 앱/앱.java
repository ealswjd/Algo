import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7579
public class Main {
	static final int MEMORY=0, COST=1;
	static int N, M;
	static int[][] apps;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 앱 개수
		M = Integer.parseInt(st.nextToken()); // 필요한 메모리
		apps = new int[N][2];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			apps[i][MEMORY] = Integer.parseInt(st.nextToken());
		}//for
		
		int total = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			apps[i][COST] = Integer.parseInt(st.nextToken());
			total += apps[i][COST];
		}//for
		br.close();
		
		dp = new int[total+1];
		
		System.out.print(getMinCost(total));
	}//main

	private static int getMinCost(int total) {
		
		for(int i=0; i<N; i++) {
			for(int j=total; j>=apps[i][COST]; j--) {
				dp[j] = Math.max(dp[j], apps[i][MEMORY] + dp[j - apps[i][COST]]);
			}//for j
		}//for i
		
		for(int cost=0; cost<=total; cost++) {
			if(dp[cost] >= M) return cost;
		}//for 
		
		return 0;
	}//getMinCost

}//class