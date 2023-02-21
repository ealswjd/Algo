package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1244
public class Main_1244_스위치켜고끄기 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine())+1; // 스위치 개수
		int[] switchArr = new int[cnt]; // 스위치 상태
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<cnt; i++) {
			switchArr[i] = Integer.parseInt(st.nextToken());
		}
		
		int studentCnt = Integer.parseInt(br.readLine()); // 학생수
		for(int i=0; i<studentCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken()); // 성별(남1 여2)
			int num = Integer.parseInt(st.nextToken()); // 받은 번호
			
			if(gender == 1) { // 남자
				for(int idx=num; idx<cnt; idx+=num) {
					switchArr[idx] ^= 1; // 받은 수의 배수일 경우 상태 변경
				}
			}else { // 여자
				int l = num-1; // 왼쪽
				int r = num+1; // 오른쪽
				while(l>0 && r < cnt) {
					if(switchArr[l] == switchArr[r]) { // 대칭이면
						l--; // 범위 확장
						r++;
					}else break; // 다르면 중단
				}
				l++; // 마지막에 한칸씩 추가된 것 제외
				r--;
				while(l <= r) { // l부터 r까지 상태 변경
					switchArr[l++] ^= 1;			
				}
			}
			
		}//for i
		
		// 출력
		StringBuilder result = new StringBuilder();
		for(int i=1; i<switchArr.length; i++) {
			result.append(switchArr[i] + " ");
			if(i%20 == 0) result.append("\n");
		}
		System.out.println(result);
		br.close();

	}//main

}//class
