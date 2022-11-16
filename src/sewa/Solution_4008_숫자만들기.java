package sewa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 4008. [모의 SW 역량테스트] 숫자 만들기
public class Solution_4008_숫자만들기 {
	static int N, max, min;
	static int[] numbers;
	static int[] operators;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			sb.append("#"+tc+" ");
			max=Integer.MIN_VALUE;
			min=Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			numbers = new int[N];
			operators = new int[4];
			
			// 각 연산자의 개수 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				operators[i] = Integer.parseInt(st.nextToken());	
			}
			
			// 수식에 사용되는 숫자 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}

			operation(0, numbers[0]);
			sb.append(max-min).append("\n");
		}//for tc
		
		System.out.println(sb);

	}//main

	private static void operation(int cnt, int sum) {
		if(cnt == N-1) {
			max = Math.max(sum, max);
			min = Math.min(sum, min);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(operators[i] == 0) continue;
			operators[i]--;
			if(i==0) 		operation(cnt+1, sum+numbers[cnt+1]);								
			else if(i==1) 	operation(cnt+1, sum-numbers[cnt+1]);					
			else if(i==2) 	operation(cnt+1, sum*numbers[cnt+1]);	
			else if(i==3) 	operation(cnt+1, sum/numbers[cnt+1]);				
			operators[i]++;			
		}
		
	}//operation



}//class


/*

10
5
2 1 0 1
3 5 3 7 9
6
4 1 0 0
1 2 3 4 5 6 
5
1 1 1 1
9 9 9 9 9 
6
1 4 0 0
1 2 3 4 5 6 
4
0 2 1 0
1 9 8 6
6
2 1 1 1
7 4 4 1 9 3 
7
1 4 1 0
2 1 6 7 6 5 8 
8
1 1 3 2
9 2 5 3 4 9 5 6 
10
1 1 5 2
8 5 6 8 9 2 6 4 3 2 
12
2 1 6 2
2 3 7 9 4 5 1 9 2 5 6 4


------>

#1 24
#2 8
#3 144
#4 8
#5 91
#6 150
#7 198
#8 2160
#9 46652
#10 701696

*/