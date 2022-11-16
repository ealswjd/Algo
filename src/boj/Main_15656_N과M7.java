package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15656
// 15656. N과 M (7)
public class Main_15656_N과M7 {
	static int N, M;
	static int[] arr, numbers;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		numbers = new int[M];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		f(0);
		System.out.println(sb);

	}//main

	static void f(int cnt) {
		if(cnt == M) {
			for(int i=0; i<M; i++) {
				sb.append(numbers[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			numbers[cnt] = arr[i];
			f(cnt+1);
		}
		
	}

}//class
