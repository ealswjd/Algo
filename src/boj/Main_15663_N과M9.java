package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15663
// 15663. N과 M (9)
public class Main_15663_N과M9 {
	static int N, M;
	static int[] arr, numbers;
	static boolean[] isSelected;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		isSelected = new boolean[N];
		numbers = new int[M];
		
		st = new StringTokenizer(br.readLine(), " ");
		int max = 0;
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
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
		int prev = -1;
		for(int i=0; i<N; i++) {
			int now = arr[i];			
			if(isSelected[i] || now == prev) continue;
			
			prev = now;
			numbers[cnt] = now;
			isSelected[i] = true;
			f(cnt+1);
			isSelected[i] = false;
		}
		
	}

}//class
