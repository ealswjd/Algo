package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/17478
public class Main_17478_재귀함수가뭔가요 {
	
	private static StringBuilder result;
	private static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cnt = Integer.parseInt(br.readLine()); // 재귀 횟수
		result = new StringBuilder(); // 출력 결과
		result.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");					
		
		StringBuilder underbar = new StringBuilder(); // 언더바
		print(cnt, underbar); // 재귀. 횟수랑 언더바를 인자값으로 넘김
		
		System.out.println(result); // 출력
		br.close();
	}//main

	private static void print(int n, StringBuilder underbar) {
		if(n==0) {
			result.append(underbar + "\"재귀함수가 뭔가요?\"\n");
			result.append(underbar + "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
			result.append(underbar + "라고 답변하였지.\n");
			return;
		}				
		
		result.append(underbar + "\"재귀함수가 뭔가요?\"\n");
		result.append(underbar + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
		result.append(underbar + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
		result.append(underbar + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
		
		print(n-1, prev(cnt-n)); 
		
		result.append(underbar + "라고 답변하였지.\n");
	}//print
	
	private static StringBuilder prev(int num) {
		StringBuilder underbar = new StringBuilder();
		for(int i=0; i<=num; i++) {
			underbar.append("____");
		}
		return underbar;
	}//prev


}//class
