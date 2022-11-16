package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16234
public class Main_16234_인구이동 {
	static int N, L, R, sum;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static ArrayList<int[]> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][N];
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		int day = move();				
		System.out.println(day);

	}//main

	private static int move() {
		int day = 0;
		while(true) {
			boolean flag = false;
			visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					sum = 0;
					if(!visited[i][j]) {
						bfs(i, j);
						if(list.size() > 1) {
							movement();
							flag = true;
						}
					}
				}
			}
			
			if(!flag) return day;
			day++;
		}//while

	}//move

	
	private static void movement() {
		int value = sum / list.size();
		
		for(int[] tmp : list) {
			map[tmp[0]][tmp[1]] = value;
		}
		
	}//movement

	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {x, y});
		list = new ArrayList<>();
		list.add(new int[] {x, y});
		visited[x][y] = true;
		sum = map[x][y];
		
		while(!q.isEmpty()) {
			int[] xy = q.poll();
			x = xy[0];
			y = xy[1];
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
				int tmp = Math.abs(map[x][y] - Math.abs(map[nx][ny]));
				if(tmp < L || tmp > R) continue;
				q.offer(new int[] {nx, ny});
				list.add(new int[] {nx, ny});
				visited[nx][ny] = true;
				sum += map[nx][ny];
			}
		}
		
	}//bfs



}//class
