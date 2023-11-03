import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2616
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 기관차가 끌고 가던 객차의 수
		int[] sum = new int[N+1]; // 손님 수 누적합
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
		}//for
		
		int cnt = Integer.parseInt(br.readLine()); // 소형 기관차가 최대로 끌 수 있는 객차의 수
		int[][] dp = new int[4][N+1];
		br.close();
		
		for(int i=1; i<4; i++) { // 소형 기관차 3대
			for(int j=i*cnt; j<=N; j++) { // i대의 소형 기관차가 끌 수 있는 객차
				// 이전 칸에서 구한 인원수 최댓값과 vs 이전 기관차에서 구한 인원수 + 현재 인원수
				dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-cnt] + sum[j] - sum[j-cnt]);
			}//for j
		}//for i

		System.out.print(dp[3][N]);
	}//main

}//class