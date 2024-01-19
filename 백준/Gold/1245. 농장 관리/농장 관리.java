import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1245
public class Main {
	static final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int N, M;
	static int[][] map;
	static boolean[][] mountain;
	static List<int[]> list;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		mountain = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		br.close();
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0 || mountain[i][j]) continue;
				list = new ArrayList<>();
				dfs(i, j, map[i][j], new boolean[N][M]);
				if(isMountain()) cnt++;
			}//for j
		}//for i

		System.out.print(cnt);
	}//main

	private static boolean isMountain() {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		for(int[] pos : list) {
			q.offer(pos);
			visited[pos[0]][pos[1]] = true;
		}//for
		
		int[] cur;
		int r, c;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			for(int i=0; i<8; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
				if(map[nr][nc] > map[r][c]) return false;
			}//for
		}//while
		
		for(int[] pos : list) {
			mountain[pos[0]][pos[1]] = true;
		}//for
		return true;
	}//isMountain

	private static void dfs(int r, int c, int n, boolean[][] visited) {
		list.add(new int[] {r, c});
		visited[r][c] = true;
		
		for(int i=0; i<8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc) || visited[nr][nc] || map[nr][nc] != n) continue;
			dfs(nr, nc, n, visited);
		}//for
		
	}//dfs

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=N || c<0 || c>=M;
	}//rangeCheck

}//class