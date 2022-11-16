package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/1941
public class Main_1941_소문난칠공주 {
	static char[][] map; // 칠공주 정보
	static int[] numbers; // 멤버 선택
	static int result; // ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][5];
		
		for(int i=0; i<5; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		numbers = new int[7];
		
		dfs(0, 0);
		System.out.print(result);
	}//main
	
	// 조합
	private static void dfs(int idx, int num) {
		if(idx == 7) { // 7명 다 모았다면
			if(check()) result++; // 칠공주를 결성할 수 있는지 확인
			return;
		}
		if(num == 25) return; // 25명을 다 탐색했으면 return
		
		numbers[idx] = num; 
	
		dfs(idx+1, num+1); // 멤버 선택
		dfs(idx, num+1); // 멤버 미선택
		
	}

	// 칠공주를 결성할 수 있는지 확인
	private static boolean check() {
		boolean[][] selected = new boolean[5][5]; // 선택된 멤버 확인
		int s_cnt = 0, cnt = 0; // 이다솜파 인원, 칠공주 인원
		int x = 0, y = 0; 
		for(int i=0; i<7; i++) {
			x = numbers[i] / 5; // 행
			y = numbers[i] % 5; // 열
			selected[x][y] = true; // 선택된 멤버 체크
		}
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y}); // 큐에 일단 아무나 넣어주고
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		boolean[][] visited = new boolean[5][5]; // 방문체크 배열
		visited[x][y] = true; 
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			x = tmp[0];
			y = tmp[1];
			cnt++; // 칠공주 인원 증가
			if(map[x][y] == 'S') s_cnt++; // 이다솜파 인원 증가
			for(int i=0; i<4; i++) { // 사방탐색
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue; // 범위 체크
				if(!selected[nx][ny] || visited[nx][ny]) continue; // 선택된 멤버가 아니거나 이미 방문한 멤버인지 체크
				visited[nx][ny] = true; // 방문 체크
				q.offer(new int[] {nx, ny}); // 담아주기
			}
		}
		
		if(cnt != 7 || s_cnt < 4) return false; // 7명이 아니거나 이다솜파가 4명보다 적다면 불가능
		return true; // 해당 안되면 가능
	}



}//class

/*

YYYYY
SYSYS
YYYYY
YSYYS
YYYYY

-->  2

*/