package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_21608_상어초등학교 {
	static int N;
	static ArrayList<int[]> list = new ArrayList<>();
	static int[][] map;
	static int[] order;
	static int[] score = {0, 1, 10, 100, 1000}; // 0이면 학생의 만족도는 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		order = new int[N*N+1];
		for(int i=0, max=N*N; i<=max; i++) {
			list.add(new int[4]);
		}
		
		StringTokenizer st;
		for(int i=1, max=N*N; i<=max; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			order[i] = index;
			for(int j=0; j<4; j++) {
				list.get(index)[j] = Integer.parseInt(st.nextToken());
			}
		}
		
		setting();
		System.out.println();

	}//main

	
	/*
	1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
	2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
	3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
	*/
	private static void setting() {
		ArrayList<Postion> emptyList;
		ArrayList<Postion> favoriteList;
		for(int i=1, max=N*N; i<=max; i++) {
			emptyList = new ArrayList<>();
			favoriteList = new ArrayList<>();
			getList(0, emptyList);
			int[] tmp = list.get(order[i]);
			for(int j=0; j<4; j++) {
				getList(tmp[j], favoriteList);
			}
			
			if(favoriteList.size() == 1) {
				
			}
			
		}//for
		System.out.println();
	}//setting


	private static void getList(int num, ArrayList<Postion> list) {

		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j] == num) list.add(new Postion(i, j));
			}
		}
		
//		return list;
	}//getList
	


}//class

class Postion {
	int x;
	int y;
	
	public Postion(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	} 
	
}//Postion

/*

3
4 2 5 1 7
3 1 9 4 5
9 8 1 2 3
8 1 9 3 4
7 2 3 4 8
1 9 2 5 7
6 5 2 3 4
5 1 9 2 8
2 9 3 1 4

--> 54

*/