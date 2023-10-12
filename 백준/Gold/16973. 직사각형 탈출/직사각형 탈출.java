import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16973
public class Main {
	static int N, M, H, W;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자판 행
		M = Integer.parseInt(st.nextToken()); // 격자판 열
		visited = new boolean[N][M]; // 방문체크
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				if(st.nextToken().equals("1")) visited[i][j] = true;
			}//for j
		}//for i
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken()); // 직사각형 높이
		W = Integer.parseInt(st.nextToken()); // 직사각형 너비
		int[] rc = new int[4]; // 시작행, 시작열, 도착행, 도착열
		for(int i=0; i<4; i++) {
			rc[i] = Integer.parseInt(st.nextToken()) - 1;
		}//for
		br.close();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]) change(i, j);
			}//for j
		}//for i

		int cnt = move(rc);
		System.out.print(cnt);
	}//main

	private static void change(int r, int c) {
		for(int i=r-H+1; i<=r; i++) {
			if(i<0||i>=N) continue;
			for(int j=c-W+1; j<=c; j++) {
				if(j<0||j>=M) continue;
				visited[i][j] = true;
			}//for j
		}//for i		
	}//change

	private static int move(int[] rc) {
		// 사각형 위치 방문 체크
		visited[rc[0]][rc[1]] = true;				
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {rc[0], rc[1], 0});
		
		int r, c, cnt;
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			cnt = cur[2];
			if(r == rc[2] && c == rc[3]) return cnt;
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc, cnt+1});
			}//for
		}//while
		
		return -1;
	}//move

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r+H-1>=N || c<0 || c+W-1>=M;
	}//rangeCheck

}//class