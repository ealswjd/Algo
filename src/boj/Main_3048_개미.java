package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3048
public class Main_3048_개미 {
	static int N1, N2, T;
	static char[] arrA, arrB;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] orders = new boolean[26]; // 1그룹은 true, 2그룹은 false로 구분
		
		// 첫 번째 그룹의 개미의 수 N1과 두 번째 그룹의 개미의 수 N2가 주어진다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N1 = Integer.parseInt(st.nextToken());
		N2 = Integer.parseInt(st.nextToken());
		
		// 다음 두 개 줄에는 첫 번째 그룹과 두 번째 그룹의 개미의 순서가 주어진다.
		arrA = new char[N1];
		String a = br.readLine();
		for(int i=0; i<N1; i++) {
			arrA[i] = a.charAt(i);
			orders[arrA[i]-'A'] = true;
		}
		arrB = new char[N2];
		String b = br.readLine();
		for(int i=0; i<N2; i++) {
			arrB[i] = b.charAt(i);
			orders[arrB[i]-'A'] = false;
		}
		
		// 마지막 줄에는 T가 주어진다. 
		T = Integer.parseInt(br.readLine());
		br.close();		
		
		// T초가 지난 후에 개미의 순서를 출력한다.
		System.out.print( move(orders) );
	}//main

	private static String move(boolean[] orders) {
		int len = N1+N2;
		char[] arr = new char[len]; // 두그룹을 합친 배열
		// 그룹 채워주기
		for(int i=N1-1, idx=0; i>=0; i--) {
			arr[idx++] = arrA[i];
		}
		for(int i=0, idx=N1; i<N2; i++) {
			arr[idx++] = arrB[i];
		}
		
		// T초 동안
		while(T-->0) {
			boolean[] visited = new boolean[len]; // 바꿨는지 체크
			for(int i=0; i<len-1; i++) {
				if(check(orders, visited, arr, i)) { // 바꿀 수 있는지 확인
					char tmp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = tmp;
					visited[i] = true; // 바꿨다고 체크해주기
					visited[i+1] = true;
				}
			}//for
		}//while		
		
		return print(arr);
	}//move

	// 바꿀 수 있는지 확인
	private static boolean check(boolean[] orders, boolean[] visited, char[] arr, int i) {
		int idxA = arr[i] - 'A';
		int idxB = arr[i+1] - 'A';
		return orders[idxA] && !orders[idxB] && !visited[i];
	}//check

	// 결과 출력
	private static String print(char[] arr) {
		StringBuilder sb = new StringBuilder();
		for(char c : arr) {
			sb.append(c);
		}
		return sb.toString();
	}//print

}//class

/*
[in]
3 3
ABC
DEF
0

[out] 
CBADEF

*/