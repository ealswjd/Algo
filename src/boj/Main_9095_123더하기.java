package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9095
// 9095. 1, 2, 3 더하기
public class Main_9095_123더하기 {
	static int N, cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			cnt = 0;
			dfs(0);
			sb.append(cnt).append("\n");
		}
		
		System.out.print(sb);

	}//main

	private static void dfs(int sum) {
		if(sum == N) {
			cnt++;
			return;
		}
		if(sum > N) return;
		
		dfs(1+sum);
		dfs(2+sum);
		dfs(3+sum);
		
	}

}//class
