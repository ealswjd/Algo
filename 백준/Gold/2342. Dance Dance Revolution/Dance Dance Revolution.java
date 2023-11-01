import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2342
public class Main {
	static int cnt; // 지시사항 개수
	static ArrayList<Integer> orders; // 지시사항
	static int[][][] dp; // 힘
	static int[][] cost = {{1, 2, 2, 2, 2}
						, {0, 1, 3, 4, 3}
						, {0, 3, 1, 3, 4}
						, {0, 4, 3, 1, 3}
						, {0, 3, 4, 3, 1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		orders = new ArrayList<>();
		int order;
		while(st.hasMoreTokens()) {
			order = Integer.parseInt(st.nextToken());
			if(order == 0) break; // 입력 마지막
			orders.add(order);
		}//while
		br.close();
		
		cnt = orders.size();
		dp = new int[cnt][5][5];
		for(int i=0; i<cnt; i++) {
			for(int j=0; j<5; j++) {
				Arrays.fill(dp[i][j], -1);
			}//for
		}//for
		
		// 두 발은 처음에 중앙(0,0)에 위치
		ddr(0, 0, 0);		
		System.out.print(dp[0][0][0]);		
	}//main

	private static int ddr(int idx, int L, int R) {
		if(idx == cnt) return 0; // 다 돌았음
		if(dp[idx][L][R] != -1) return dp[idx][L][R]; // 이미 밟은 발판
		
		int next = orders.get(idx); // 다음 방향
		int costL = ddr(idx+1, next, R) + cost[L][next]; // 왼쪽 발 움직일 경우
		int costR = ddr(idx+1, L, next) + cost[R][next]; // 오른쪽 발 움직일 경우
		
		dp[idx][L][R] = Math.min(costL, costR); // 왼쪽 오른쪽 중 작은 값
		
		return dp[idx][L][R];
	}//ddr

}//class