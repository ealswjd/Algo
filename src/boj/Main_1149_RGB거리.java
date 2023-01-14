package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1149_RGB거리 {
	static int[][] cost, dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 집의 수
		cost = new int[N][3]; // 칠하는 비용
		dp = new int[N][3]; // 최소비용
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken()); // 빨강
			cost[i][1] = Integer.parseInt(st.nextToken()); // 초록
			cost[i][2] = Integer.parseInt(st.nextToken()); // 파랑
		} 
		
		// 첫번째 집 초기화
		dp[0][0] = cost[0][0];
		dp[0][1] = cost[0][1];
		dp[0][2] = cost[0][2];
		
		int min = Math.min(getMin(N-1, 0),  Math.min(getMin(N-1, 1), getMin(N-1, 2)));
		System.out.println(min);

	}//main

	private static int getMin(int N, int color) {
		
		if(dp[N][color] == 0) { // 탐색하지 않았다면 
			if(color == 0) { // 빨강
				dp[N][0] = Math.min(getMin(N-1, 1), getMin(N-1, 2)) + cost[N][0];
			}else if(color == 1) { // 초록
				dp[N][1] = Math.min(getMin(N-1, 0), getMin(N-1, 2)) + cost[N][1];				
			}else { // 파랑
				dp[N][2] = Math.min(getMin(N-1, 0), getMin(N-1, 1)) + cost[N][2];				
			}
		}//if
		
		return dp[N][color];
	}//getMin

}//class
