import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1695
public class Main {
	static int N;
	static int[][] palindrome;
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 수열의 길이
		
		numbers = new int[N+1];
		palindrome = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			Arrays.fill(palindrome[i], -1);
		}//for
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}//for
		br.close();

		make(1, N);
		System.out.print(palindrome[1][N]);
	}//main

	private static int make(int s, int e) {
		if(s >= e) return 0;
		if(palindrome[s][e] != -1) return palindrome[s][e];
		
		int cnt = 0;
		if(numbers[s] == numbers[e]) cnt = make(s+1, e-1);
		else cnt = Math.min(make(s+1, e), make(s, e-1)) + 1;		
		
		return palindrome[s][e] = cnt;
	}//make

}//class