package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15650
public class Main_15650_N과M2 {
	static int N, M;
	static int[] numbers;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 1부터 N까지
		M = Integer.parseInt(st.nextToken()); // M개를 고른 수열
		
		numbers = new int[M];
		sb = new StringBuilder();
		f(0, 1);
		
		System.out.println(sb);

	}//main

	private static void f(int cnt, int i) {
		if(cnt == M) {
			for(int j=0; j<M; j++) {
				sb.append(numbers[j] + " ");
			}
			sb.append("\n");
			return;
		}

		for(; i<=N; i++) {
			numbers[cnt] = i;

			f(cnt+1, i+1);
		}
	}

}//class
