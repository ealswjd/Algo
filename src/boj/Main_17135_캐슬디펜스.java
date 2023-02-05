package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17135
public class Main_17135_캐슬디펜스 {
	static int N, M, D, result;
	static int[][] map, copyMap;
	static boolean[] isSelected;
	static int[] posArr = new int[3];
	static ArrayList<Position> monsterList = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken()); // 거리가 D이하인 적 중에서 가장 가까운 적
		map = new int[N][M];
		isSelected = new boolean[M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		perm(0, 0);
		System.out.println(result);

	}//main

	private static void perm(int cnt, int start) {
		if(cnt == 3) {
			copyMap = copyArr(map);
			getMonsterPosition();
			int sum = 0;
			while(!gameOver()) {
				getMonsterPosition();
				sum += game();
				move();	
			}
			result = Math.max(result, sum);
			return;
		}
		for(int i=start; i<M; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			posArr[cnt] = i;
			perm(cnt+1, i+1);
			isSelected[i] = false;
		}
	}//perm

	private static void getMonsterPosition() {
		monsterList.clear();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copyMap[i][j] == 1) monsterList.add(new Position(i, j, 11));
			}
		}		
	}//getMonsterPosition

	private static boolean gameOver() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copyMap[i][j] == 1) return false;
			}
		}
		return true;
	}//gameOver

	private static void move() {		
		for(int i=N-1; i>0; i--) {
			for(int j=0; j<M; j++) {
				copyMap[i][j] = copyMap[i-1][j];				
			}
		}	
		Arrays.fill(copyMap[0], 0);
	}//move

	private static int[][] copyArr(int[][] map) {
		int[][] tmp = new int[N][M];
		for(int i=0; i<N; i++) {
			System.arraycopy(map[i], 0, tmp[i], 0, M);
		}
		return tmp;
	}//copyArr

	private static int game() {
		int cnt = 0;
		
		for(int i=0; i<3; i++) {
			Queue<Position> q = attack(posArr[i]);
			if(!q.isEmpty()) {
				Position pos = q.poll();
				if(copyMap[pos.x][pos.y] != 0) {
					copyMap[pos.x][pos.y] = 0;
					cnt++;
				}
			}
		}
				
		return cnt;
	}//game

	private static Queue<Position> attack(int idx) {
		Queue<Position> list = new PriorityQueue<>();
		
		for(int i=0; i<monsterList.size(); i++) {
			int d = getDistance(idx, monsterList.get(i));
			if(d <= D) {
				monsterList.get(i).d = d;
				list.add(monsterList.get(i));
			}
		}
		
		return list;
	}//attack

	private static int getDistance(int num, Position position) {		
		return Math.abs(num-position.y) + (N-position.x);
	}//getDistance

}//class

class Position implements Comparable<Position>{
	int x;
	int y;
	int d;
	public Position(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
	@Override
	public int compareTo(Position o) {
		return this.d == o.d ? this.y - o.y : this.d - o.d;
	}
}//Position
