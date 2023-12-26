import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13398
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 수열 길이
		int[] arr = new int[N];
		int[][] dp = new int[2][N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//for
		br.close();
				
		int max = dp[0][0] = dp[1][0] = arr[0];		
		for(int i=1; i<N; i++) {
			dp[0][i] = Math.max(arr[i], dp[0][i-1] + arr[i]);
			dp[1][i] = Math.max(dp[0][i-1], dp[1][i-1] + arr[i]);
			max = Math.max(max, Math.max(dp[0][i], dp[1][i]));
		}//for
		
		System.out.print(max);

	}//main

}//class