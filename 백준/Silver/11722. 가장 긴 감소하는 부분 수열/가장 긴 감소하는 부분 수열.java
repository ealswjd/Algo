import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11722
public class Main {
	static int[] A, dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		A = new int[N+1];
		dp = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}//for
		br.close();
		
		int max = 1;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<i; j++) {
				if(A[j] > A[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
					max = Math.max(max, dp[i]);
				}//if
			}//for j
		}//for i
				
		System.out.print(max);
	}//main

}//class