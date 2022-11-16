package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2954
public class Main_2954_창영이의일기장 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine(); // 문자 ‘p’를 넣어 정리한 문장
		StringBuilder sb = new StringBuilder(); // 원래 문자열
		
		for(int i=0, size=str.length(); i<size; i++) {
			char c = str.charAt(i);
			sb.append(c); // 원래 문자열에 넣어주고
			
			// 모음에 해당하는지 확인. 해당 문자가 모음이라면
			if("aeiou".indexOf(c) > -1 || "AEIOU".indexOf(c) > -1) 
				i+= 2; // p와 모음 하나 더 쓴 것을 제외하기 위해 인덱스 증가
		}
		
		System.out.print(sb); // 결과 출력

	}//main

}//class
