package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/3085
public class Main_3085_사탕게임 {
	static int N, max=-1;
	static char[][] map, tmp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // N×N크기의 보드
		map = new char[N][N]; // 게임보드
		tmp = new char[N][N]; // 게임보드 복사
		
		// 빨간색은 C, 파란색은 P, 초록색은 Z, 노란색은 Y로 주어진다.
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			tmp[i] = map[i].clone();
		}		
		br.close();
		
		// 상근이가 먹을 수 있는 사탕의 최대 개수를 출력한다.
		getMax(); // 최대 개수 구하기
		game(); // 게임 시작
		System.out.print(max); // 최대 개수 출력
	}//main

	private static void game() {
		
		for(int i=0; i<N; i++) {
			for(int j=1; j<N; j++) {
				char r = map[i][j-1]; // 행 사탕
				char c = map[j-1][i]; // 열 사탕
				// 사탕 색이 다르다면 교환
				if(r!=map[i][j]) change(i, j-1, i, j, r); 
				if(c!=map[j][i]) change(j-1, i, j, i, c);
			}//for j
		}//for i
		
	}//game

	// 이전 행, 열, 현재 행, 열, 사탕색
	private static void change(int pr, int pc, int r, int c, char color) {
		// 사탕 교환
		map[pr][pc] = map[r][c];
		map[r][c] = color;	
		// 최대 개수 구하기
		getMax();
		// 원상복귀
		map[r][c] = tmp[r][c];
		map[pr][pc] = tmp[pr][pc];
	}

	private static void getMax() {
		int rCnt=1, cCnt=1; // 행,열에서 먹을 수 있는 사탕의 최대 개수
		for(int i=0; i<N; i++) {
			rCnt=1; cCnt=1;
			for(int j=1; j<N; j++) {
				char r = map[i][j-1];
				char c = map[j-1][i];
				if(r==map[i][j]) rCnt++; // 사탕이 같다면 개수 증가
				else {
					r = map[i][j];
					rCnt=1;
				}
				if(c==map[j][i]) cCnt++; // 사탕이 같다면 개수 증가
				else {
					c = map[j][i];
					cCnt=1;
				}
				// 최대 개수 갱신
				max = Math.max(max, cCnt);
				max = Math.max(max, rCnt);
			}//for j
		}//for i
	}//getMax

}//class


/*

3
CCP
CCP
PPC
답 : 3

*/
