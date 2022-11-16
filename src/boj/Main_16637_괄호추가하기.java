package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_16637_괄호추가하기 {
	static int max = Integer.MIN_VALUE;
	static int[] num; // 숫자
	static char[] operator; // 연산자

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray(); // 일단 다 받아서
		
		num = new int[N/2+1];
		operator = new char[N/2];
		for(int i=0, n=0, o=0; i<N; i++) {
			if(i%2 == 0) num[n++] = arr[i] - '0'; // 숫자면 숫자배열에 넣어주고
			else operator[o++] = arr[i]; // 연산자면 연산자 배열에 넣어주고
		}		

		solution(0, num[0]); // 연산자 index, 계산값
		System.out.print(max); // 결과 출력
	}//main

	private static void solution(int idx, int result) { // 연산자 index, 계산값
		if(idx == operator.length) {
			max = Math.max(max, result); // 최댓값 갱신
			return;
		}
				
		solution(idx+1, cal(idx, result, num[idx+1])); // 괄호 안 쓰고 계산
		// 괄호 쓰고 계산
		if(idx+1 < operator.length) {
			solution(idx+2, cal(idx, result, cal(idx+1, num[idx+1], num[idx+2])));
		}
	}//solution

	private static int cal(int idx, int a, int b) {
		switch(operator[idx]) {
		case '+' :
			return a + b;
		case '-' :
			return a - b;
		case '*' :
			return a * b;
		}
		
		return 1;
	}

	

}//class

/*

9
3+8*7-9*2

*/