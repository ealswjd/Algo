package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/2559
// 2559번) 수열
public class Sequence {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 전체 날짜의 수
        int k = Integer.parseInt(st.nextToken()); // 연속적인 날짜의 수
        
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int start = 0, end = 0; // 시작점, 종료점
        int sum = 0, day = 0; // 온도 합, 일
        int max = Integer.MIN_VALUE; // 가장 큰 수를 구하기 위해 작은수로 초기화
        while(start < n) {
        	if(day == k) { // k일만큼 지났으면
        		max = Math.max(max, sum); // k일동안의 온도 합과 max값을 비교하여 큰 값 대입
        		sum -= arr[start++]; // 합에서 맨 앞의 온도 감소
        		day--; // 일 수 감소
        	}else if(end == n) break; // 끝까지 다 돌았으면 break
        	else {
        		sum += arr[end++]; // 그 다음날 온도 더해주기
        		day++; // 일 수 증가
        	}
        }
         
        System.out.print(max);
    }//main  
    
}//class
