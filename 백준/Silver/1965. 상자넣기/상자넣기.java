import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1965
public class Main {
	static int N;
	static int[] dp, box;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N];
		box = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = 0;
		for(int i=0; i<N; i++) {
			box[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
            
			for(int j=0; j<i; j++) {
				if(box[i] > box[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
			}//for j
            
			max = Math.max(max, dp[i]);
		}//for

		System.out.print(max);
	}//main

}//class