import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14501
public class Main {
	static final int T=0, P=1;
	static int N, max;
	static int[][] info;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		info = new int[N+1][2];
		dp = new int[N+2];
		
		StringTokenizer st;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][T] = Integer.parseInt(st.nextToken());
			info[i][P] = Integer.parseInt(st.nextToken());
		}//for
		br.close();		

		getMax();					
		System.out.print(dp[1]);
	}//main

	private static void getMax() {
		int next;
		for(int i=N; i>0; i--) {
			next = i + info[i][T];
			if(next > N+1) dp[i] = dp[i+1];
			else dp[i] = Math.max(dp[i+1], dp[next] + info[i][P]);
		}//for
	}//getMax

}//class