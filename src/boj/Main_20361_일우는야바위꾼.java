package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20361_일우는야바위꾼 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 결과를 담을 StringBuilder

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 종이컵 수 
        int X = Integer.parseInt(st.nextToken()); // 공이 들어있는 위치(왼쪽부터)
        int K = Integer.parseInt(st.nextToken()); // 컵의 위치를 맞바꾸는 횟수

        int[] arr = new int[N+1]; // 컵 배열
        for(int i=1; i<=N; i++) { // 위치 인덱스로 초기화
            arr[i] = i;
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr = f(a, b, arr); // 컵 위치 맞바꾸기
        }
        int result = -1; // 공이 있는 위치
        for(int i=1; i<=N; i++) {
            if(arr[i] == X) { // 공이 들어있다면
                result = i;
                break; 
            }
        }
	
		System.out.println(result); // 결과 출력
	}//main

	// 컵의 위치 바꾸기
	private static int[] f(int a, int b, int[] arr) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;	
		
		return arr;
	}

}//class
