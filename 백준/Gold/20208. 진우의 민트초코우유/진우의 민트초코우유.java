import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20208
public class Main {
	static int N, M, H, max;
	static int[] jinwoo;
	static boolean[][] visited;
	static ArrayList<int[]> mintList;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 민초마을의 크기
		M = Integer.parseInt(st.nextToken()); // 진우의 초기체력 
		H = Integer.parseInt(st.nextToken()); // 민트초코우유를 마실때 마다 증가하는 체력의 양
		
		jinwoo = new int[2];
		mintList = new ArrayList<>();
		
		// 진우의 집은 1, 민트초코우유는 2, 빈 땅은 0
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					jinwoo[0] = i;
					jinwoo[1] = j;
				}else if(map[i][j] == 2) mintList.add(new int[] {i, j});
			}//for j
		}//for i
		
		visited = new boolean[N][N];
		
		for(int[] cur : mintList) {
			int len = rangeCheck(cur[0], cur[1], jinwoo[0], jinwoo[1]);
			if(len > M) continue;
			visited[cur[0]][cur[1]] = true;
			dfs(cur[0], cur[1], M-len+H, 1);
			visited[cur[0]][cur[1]] = false;
		}//for
		
		System.out.print(max);
	}//main

	private static void dfs(int r, int c, int h, int cnt) {
		if(rangeCheck(r, c, jinwoo[0], jinwoo[1]) <= h) {
			max = Math.max(max, cnt);			
		}//if
		
		int nr, nc;
		for(int[] next : mintList) {
			nr = next[0];
			nc = next[1];
			if(visited[nr][nc]) continue;
			int len = rangeCheck(r, c, nr, nc);
			if(len > h) continue;
			visited[nr][nc] = true;
			dfs(nr, nc, h-len+H, cnt+1);
			visited[nr][nc] = false;
		}//for
		
	}//dfs

	private static int rangeCheck(int r, int c, int r2, int c2) {
		return Math.abs(r - r2) + Math.abs(c - c2);
	}//rangeCheck

}//class