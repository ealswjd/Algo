import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14716
public class Main {
	static int N, M;
	static int[][] map;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
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
				if(map[i][j] == 1) {
					dfs(i, j);
					cnt++;
				}//if
			}//for j
		}//for i		
		
		System.out.print(cnt);
	}//main

	private static void dfs(int r, int c) {
		map[r][c] = 2;
		int nr, nc;
		for(int i=0; i<8; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if(rangeCheck(nr, nc)) continue;
			dfs(nr, nc);
		}//for
		
	}//dfs

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=N || c<0 || c>=M || map[r][c] != 1;
	}//rangeCheck

}//class