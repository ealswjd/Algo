package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1769
// 1769. 3의 배수
public class Main_1769_3의배수 {
	static int cnt;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String X = br.readLine();
		StringBuilder sb = new StringBuilder();
		
		dfs(X, 1, X.charAt(0)-'0');
		sb.append(cnt).append("\n");
		sb.append(flag ? "YES" : "NO");
		System.out.print(sb);

	}//main

	private static void dfs(String x, int idx, int sum) {
		if(x.length() == 1) {
			if(sum%3==0) flag = true;
			return;
		}
		if(idx == x.length()) {
			cnt++;
			if((int)Math.log10(sum)+1 == 1) {
				if(sum%3==0) flag = true;
				return;
			}
			x = String.valueOf(sum);
			idx = 1;
			sum = x.charAt(0) - '0';			
		}
		dfs(x, idx+1, sum+(x.charAt(idx)-'0'));
	}


}//class
