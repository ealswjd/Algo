import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16938
public class Main {
	static int N, L, R, X, result;
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 문제 개수
		L = Integer.parseInt(st.nextToken()); // 난이도 최소 기준
		R = Integer.parseInt(st.nextToken()); // 난이도 최대 기준
		X = Integer.parseInt(st.nextToken()); // 가장 어려운 문제와 가장 쉬운 문제의 난이도 차이
		
		numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}//for
		
		dfs(0, 0, 0, 10000001, 0);
		System.out.print(result);
	}//main

	private static void dfs(int cur, int cnt, int sum, int min, int max) {
		if(cur==N) {
			if(cnt>1 && L<=sum && sum<=R && max-min>=X) result++;
			return;
		}//if
		
		// 문제 선택
		dfs(cur+1, cnt+1, sum+numbers[cur], Math.min(min, numbers[cur]), Math.max(max, numbers[cur]));	
		// 문제 선택 X
		dfs(cur+1, cnt, sum, min, max);					
	}//dfs

}//class