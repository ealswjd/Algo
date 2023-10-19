import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14442
public class Main {
	static int N, M, K, min;
	static char[][] map;
	static int[][][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new int[K+1][N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}//for i
		br.close();
		
		min = 987654321;
		bfs();

		System.out.print(min);
	}//main

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0, 1, 0});
		for(int i=0; i<=K; i++) visited[i][0][0] = 1;
		
		int[] cur;
		int r, c, move, kCnt;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			move = cur[2];
			kCnt = cur[3];
			
			if(r==N-1 && c==M-1) {
				min = Math.min(min, move);
				continue;
			}//if
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(rangeCheck(nr, nc)) continue;
				if(map[nr][nc] == '1') { // 벽
					if(kCnt >= K) continue;
					if(visited[kCnt+1][nr][nc]==0 || visited[kCnt+1][nr][nc] > move+1) {
						visited[kCnt+1][nr][nc] = move+1;
						q.offer(new int[] {nr, nc, move+1, kCnt+1});												
					}
				}else {//빈 공간
					if(visited[kCnt][nr][nc]==0 || visited[kCnt][nr][nc] > move+1) {
						visited[kCnt][nr][nc] = move+1;
						q.offer(new int[] {nr, nc, move+1, kCnt});						
					}
				}//else
			}//for
		}//while
		
		if(min==987654321) min = -1;
	}//bfs

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=N || c<0 || c>=M;
	}//rangeCheck

}//class