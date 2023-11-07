import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17404
public class Main {
	static final int R=0, G=1, B=2, INF=1000000;
	static int N;
	static int[][] rgb, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		rgb = new int[N][3];
		dp = new int[N][3];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				rgb[i][j] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		br.close();
		
		System.out.print(getMin());
	}//main

	private static int getMin() {
		int min = INF;
		
		// 첫 번째 집 R, G, B 순서대로 칠함.
		for(int k=0; k<3; k++) {
			for(int i=0; i<3; i++) {
				if(i==k) dp[0][i] = rgb[0][i];
				else dp[0][i] = INF;
			}//for i
			
			// 두 번째 집부터 R, G, B로 칠했을 때 누적 비용 최솟값 갱신
			for(int i=1; i<N; i++) {
				dp[i][R] = rgb[i][R] + Math.min(dp[i-1][G], dp[i-1][B]);
				dp[i][G] = rgb[i][G] + Math.min(dp[i-1][R], dp[i-1][B]);
				dp[i][B] = rgb[i][B] + Math.min(dp[i-1][R], dp[i-1][G]);
			}//for i
			
			for(int i=0; i<3; i++) {
				// N번 집의 색은 1번 집의 색과 같지 않아야 한다.
				if(i == k) continue; // 1번 집의 색과 같을 경우 넘어감
				min = Math.min(min, dp[N-1][i]); // 최솟값, 모든 집을 다 칠했을 때의 비용 비교
			}//for i
		}//for k
		
		return min;
	}//getMin

}//class