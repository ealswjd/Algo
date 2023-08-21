import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10942
public class Main {
	static int N;
	static int[][] palindrome;
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 수열의 크기
		numbers = new int[N+1];
		palindrome = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			Arrays.fill(palindrome[i], -1);
		}//for
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			palindrome[i][i] = 1;
		}//for
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();
		int s, e, answer;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			answer = getAnswer(s, e);
			ans.append(answer).append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static int getAnswer(int s, int e) {
		if(numbers[s] != numbers[e]) return palindrome[s][e] = 0;		
		if(palindrome[s][e] != -1) return palindrome[s][e];
		
		if(numbers[s] == numbers[e]) palindrome[s][e] = 1;
		if(s+1 >= e-1) return palindrome[s][e];
		
		return palindrome[s][e] = getAnswer(s+1, e-1);
	}//getAnswer

}//class