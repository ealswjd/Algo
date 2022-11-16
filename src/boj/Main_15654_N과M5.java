package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15654  
// 15654. N과 M (5)
public class Main_15654_N과M5 {
	static int N, M;
	static int[] arr, numbers;
	static boolean[] isSelected;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		isSelected = new boolean[N];
		numbers = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		Arrays.sort(arr);
		perm(0);
		System.out.println(sb);

	}//main

	private static void perm(int cnt) {
		if(cnt == M) {
			for(int num : numbers) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			numbers[cnt] = arr[i];
			isSelected[i] = true;
			perm(cnt+1);
			isSelected[i] = false;
		}
		
	}//perm

}//class
