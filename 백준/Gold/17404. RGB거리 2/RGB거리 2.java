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
		for(int k=0; k<3; k++) {
			for(int i=0; i<3; i++) {
				if(i==k) dp[0][i] = rgb[0][i];
				else dp[0][i] = INF;
			}//for i
			
			for(int i=1; i<N; i++) {
				dp[i][R] = Math.min(dp[i-1][G], dp[i-1][B]) + rgb[i][R];
				dp[i][G] = Math.min(dp[i-1][R], dp[i-1][B]) + rgb[i][G];
				dp[i][B] = Math.min(dp[i-1][R], dp[i-1][G]) + rgb[i][B];
			}//for i
			
			for(int i=0; i<3; i++) {
				if(i == k) continue;
				min = Math.min(min, dp[N-1][i]);
			}//for i
		}//for k
		return min;
	}//getMin

}//class