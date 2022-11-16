package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15651
public class Main_15651_N과M3 {
	static int N, M;
	static int[] numbers;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		br.close();
		
		numbers = new int[M];
		sb = new StringBuilder();
		f(0);
		
		System.out.println(sb);

	}

	private static void f(int cnt) {
		if(cnt == M) {
			for(int n : numbers) {
				sb.append(n + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			numbers[cnt] = i;
			f(cnt+1);
		}
		
	}

}
