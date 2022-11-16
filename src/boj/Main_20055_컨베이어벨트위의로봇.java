package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20055
// 20055. 컨베이어 벨트 위의 로봇
public class Main_20055_컨베이어벨트위의로봇 {
	static int N, K;
	static int[] belt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 길이가 N인 컨베이어 벨트가 있고, 길이가 2N인 벨트가 이 컨베이어 벨트를 위아래로 감싸며 돌고 있다.
		N = Integer.parseInt(st.nextToken()); 
		K = Integer.parseInt(st.nextToken()); // 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료
		
		belt = new int[2*N];

	}//main

}//class
