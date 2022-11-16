package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15652
public class Main_15652_N과M4 {
	static int N, M;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[M];
		
		f(0, sb);
		System.out.println(sb);

	}//main

	private static void f(int cnt, StringBuilder sb) {
		if(cnt == M) {
			for(int num : numbers) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(cnt == 0) numbers[cnt] = i;
			else if(numbers[cnt-1] <= i) numbers[cnt] = i;
			else continue;

			f(cnt+1, sb);
		}
		
	}

}//class
