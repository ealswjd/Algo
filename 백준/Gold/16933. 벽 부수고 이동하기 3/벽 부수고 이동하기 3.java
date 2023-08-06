import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16933
public class Main {
	static int N, M, K;
	static final char WALL = '1';
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		K = Integer.parseInt(st.nextToken()); // 벽을 부술 수 있는 횟수
		
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}//for
		br.close();
		
		// (1, 1)에서 (N, M)의 위치까지 이동
		System.out.print( getMin(0, 0) );
	}//main

	private static int getMin(int r, int c) {
		visited = new boolean[N][M][K+1];
		// 벽 안 부수고 이동 0, 벽 부수고 이동 1
		visited[r][c][0] = true; 
		int cnt = 1; // 이동 횟수
		int wall = 0; // 벽 부순 횟수
		int day = 1;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c, cnt, wall, day});
		
		int max = Integer.MAX_VALUE, min = max;
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0]; 
			c = cur[1];
			cnt = cur[2]; 
			wall = cur[3]; 
			day = cur[4];
			if(arrive(r, c)) {
				min = Math.min(min, cnt);
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(rangeCheck(nr, nc)) continue;
				// 벽일 때
				if(map[nr][nc] == WALL && wall < K) {					
					if(day == 0) { // 밤
						q.offer(new int[] {r, c, cnt+1, wall, 1});					
					}else { // 낮
						if(visitCheck(wall+1, nr, nc)) continue;
						visited[nr][nc][wall+1] = true;
						q.offer(new int[] {nr, nc, cnt+1, wall+1, 0});															
					}
				}else if(map[nr][nc] != WALL){ // 벽이 아닐 때
					if(visitCheck(wall, nr, nc)) continue;
					visited[nr][nc][wall] = true;
					q.offer(new int[] {nr, nc, cnt+1, wall, (day+1)%2});				
				}//else
			}//for
		}//while
		
		if(min == max) return -1;
		return min;
	}//getMin

	private static boolean visitCheck(int wall, int r, int c) {
		return visited[r][c][wall];
	}//visitCheck

	private static boolean arrive(int r, int c) {
		return r == N-1 && c == M-1;
	}//arrive

	private static boolean rangeCheck(int r, int c) {		
		return r < 0 || r >= N || c < 0 || c >= M;
	}//rangeCheck

}//class