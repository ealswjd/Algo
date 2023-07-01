import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17836
public class Main {
	static int N, M, T;
	static int[][] map;
	static boolean[][][] visited;
	static int[] gram;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 성의 크기
		M = Integer.parseInt(st.nextToken()); // 성의 크기
		T = Integer.parseInt(st.nextToken()); // 저주의 제한 시간
		
		map = new int[N][M]; // 지도
		visited = new boolean[2][N][M]; // 방문체크 
		gram = new int[2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) gram = new int[] {i, j}; // 명검 위치
			}//for j
		}//for i
		br.close();
		
		int time = bfs();
		if(time>T) System.out.print("Fail");
		else System.out.print(time);

	}//main

	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		int r = 0, c = 0, g = 0, t=0;
		visited[0][r][c] = true;
		visited[1][r][c] = true;
		q.offer(new int[] {r, c, g, t});
		int time = 100004;
		
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0]; // 행
			c = cur[1]; // 열
			g = cur[2]; // 검 보유x:0 보유:1
			t = cur[3]; // 시간
			if(r==N-1 && c==M-1) time = Math.min(time, t);
			if(r==gram[0] && c==gram[1]) g = 1;
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(rangeCheck(nr, nc, g)) continue; // 범위, 방문 체크
				if(magicWallCheck(nr, nc, g)) continue; // 명검 보유 체크
				q.offer(new int[] {nr, nc, g, t+1});
				visited[g][nr][nc] = true;
			}//for
		}//while
		
		return time;
	}//bfs

	private static boolean magicWallCheck(int r, int c, int g) {
		return map[r][c]==1 && g==0;
	}//magicWallCheck

	private static boolean rangeCheck(int r, int c, int g) {
		return r<0 || r>=N || c<0 || c>=M || visited[g][r][c];
	}//bfs

}//class