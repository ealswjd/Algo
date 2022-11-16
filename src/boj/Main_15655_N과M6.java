package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15655
// 15655. N과 M (6)
public class Main_15655_N과M6 {
	static int N, M;
	static int[] arr, numbers;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		numbers = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}		
		Arrays.sort(arr);
		
		sb = new StringBuilder();
		perm(0, 0);
		System.out.println(sb);
	}//main

	private static void perm(int cnt, int i) {
		if(cnt == M) {
			for(int num : numbers) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(; i<N; i++) {
			numbers[cnt] = arr[i];
			perm(cnt+1, i+1);
		}
		
	}//perm

}//class
