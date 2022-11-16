package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14888_연산자끼워넣기 {
	static int[] numbers, operators;
	static int N, min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		operators = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		calculation(1, numbers[0]);
		System.out.println(max);
		System.out.print(min);

	}//main

	private static void calculation(int idx, int sum) {
		if(idx == N) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		
		for(int o=0; o<4; o++) {
			if(operators[o] == 0) continue;
			operators[o]--;
			
			if(o==0) calculation(idx+1, sum + numbers[idx]);
			else if(o==1) calculation(idx+1, sum - numbers[idx]);
			else if(o==2) calculation(idx+1, sum * numbers[idx]);
			else if(o==3) calculation(idx+1, sum / numbers[idx]);
			
			operators[o]++;
		}

		
	}

}//class
