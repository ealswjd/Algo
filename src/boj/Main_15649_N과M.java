package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15649
public class Main_15649_N과M {
	static StringBuilder sb;
	static int[] numbers;
	static boolean[] isSelected;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[M];
		isSelected = new boolean[N+1];
		sb = new StringBuilder();
		
		f(0);
		System.out.println(sb);
	}
	
	private static void f(int cnt) {
		if(cnt == M) {
			for(int i=0; i<M; i++) {
				sb.append(numbers[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for(int i=1; i<=N; i++) {
			// 중복체크 필요!
			if(isSelected[i]) continue;
			// 해당 수 선택
			numbers[cnt] = i;
			isSelected[i] = true;
			// 다음 수 선택
			f(cnt+1);
			isSelected[i] = false;
		}
		
	}

}
