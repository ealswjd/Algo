import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14002
public class Main {
	static int N;
	static int[] A, dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N+1];
		dp = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}//for
		
		int max = 1;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<i; j++) {
				if(A[j] < A[i]) dp[i] = Math.max(dp[i], dp[j]+1);
			}//for j			
			max = Math.max(max, dp[i]);
		}//for i		
		
		print(max);
	}//main

	private static void print(int result) {
		Stack<Integer> stack = new Stack<>();
		int max = result;

		for(int i=N; i>=1; i--) {
			if(max == dp[i]) {
				stack.add(A[i]);
				max--;
			}//if
		}//for
		
		StringBuilder ans = new StringBuilder();
		ans.append(result).append('\n');
		
		while(!stack.isEmpty()) {
			ans.append(stack.pop()).append(' ');
		}//while
		
		System.out.print(ans);
	}//print

}//class