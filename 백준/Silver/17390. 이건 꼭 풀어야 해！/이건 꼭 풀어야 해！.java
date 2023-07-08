import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17390
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}//for
		
		Arrays.sort(A);
		int[] sum = new int[N+1];
		sum[1] = A[0];
		for(int i=2; i<=N; i++) {
			sum[i] += sum[i-1] + A[i-1];
		}//for
		
		int l, r;
		StringBuilder ans = new StringBuilder();
		while(Q-->0) {
			st = new StringTokenizer(br.readLine());
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			ans.append(sum[r] - sum[l-1]).append("\n");
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

}//class