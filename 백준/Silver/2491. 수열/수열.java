import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2491
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//for
		br.close();
		
		int max = 1;
		int[][] dp = new int[N][2];
		dp[0][0] = 1; // 작아짐
		dp[0][1] = 1; // 커짐
		for(int i=1; i<N; i++) {
			dp[i][0] += arr[i] <= arr[i-1] ? dp[i-1][0] + 1 : 1; // 작아짐
			dp[i][1] += arr[i] >= arr[i-1] ? dp[i-1][1] + 1 : 1; // 커짐
			max = Math.max(max, Math.max(dp[i][0], dp[i][1])); // 가장 긴 길이
		}//for

		System.out.print(max);
	}//main

}//class