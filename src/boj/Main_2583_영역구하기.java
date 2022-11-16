package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2583
public class Main_2583_영역구하기 {
	static int N, M, area;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][M];
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			
			for(int i=N-x2; i<N-x1; i++) {
				for(int j=y1; j<y2; j++) {
					visited[i][j] = true;
				}
			}
			
		}//for k
		
		int cnt = 0; // 영역 개수
		ArrayList<Integer> list = new ArrayList<>(); // 영역 넓이 리스트
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]) continue;
				area = 0;
				dfs(i, j);
				cnt++;
				list.add(area);
			}
		}//for
		
		Collections.sort(list); // 영역 넓이 오름차순 정렬
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		for(int n : list) {
			sb.append(n+" ");
		}
		
		System.out.print(sb.toString().trim());
		
	}//main

	private static void dfs(int x, int y) {
		visited[x][y] = true; // 방문체크
		area++; // 영역 넓이 증가
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
			dfs(nx, ny);
		}
		
	}//dfs

}//class
