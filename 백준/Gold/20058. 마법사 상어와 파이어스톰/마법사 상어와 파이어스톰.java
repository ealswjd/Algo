import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20058
public class Main {
	static int N, Z;
	static int[][] map;
	static int sum;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 제곱
		int Q = Integer.parseInt(st.nextToken()); // 파이어스톰 횟수
		
		Z = (int) Math.pow(2, N); // 2^N
		map = new int[Z][Z];
		
		sum = 0;
		for(int i=0; i<Z; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<Z; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sum += map[i][j];
			}//for j
		}//for i
		
		StringBuilder ans = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int L;
		while(Q-->0) {
			L = Integer.parseInt(st.nextToken());			
			stepL(L); // 시계 방향으로 90도 회전
			minusIce();
		}//while
		br.close();
				
		ans.append(sum).append("\n"); // 남아있는 얼음의 합
		ans.append( getMax() ); // 가장 큰 덩어리가 차지하는 칸의 개수
		System.out.print(ans);
	}//main

	private static int getMax() {
		int cnt = 0;
		
		boolean[][] visited = new boolean[Z][Z];
		for(int i=0; i<Z; i++) {
			for(int j=0; j<Z; j++) {
				if(visited[i][j] || map[i][j]<1) continue;
				visited[i][j] = true;
				cnt = Math.max(cnt, bfs(i, j, visited));
			}//for j
		}//for i
		
		return cnt;
	}//getMax

	private static int bfs(int r, int c, boolean[][] visited) {
		int cnt = 1;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		
		int[] cur;
		int nr, nc;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc});
				cnt++;
			}//for
		}//while
		
		return cnt;
	}//bfs

	private static void minusIce() {
		Queue<int[]> q = new LinkedList<>();

		for(int i=0; i<Z; i++) {
			for(int j=0; j<Z; j++) {
				if(iceCheck(i, j)) q.offer(new int[] {i, j});
			}//for j
		}//for i
		
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			if(map[cur[0]][cur[1]] > 0) {
				map[cur[0]][cur[1]]--;
				sum--;				
			}//if
		}//while
		
	}//minusIce

	private static boolean iceCheck(int r, int c) {
		int nr, nc;
		int cnt=0;
		for(int i=0; i<4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if(rangeCheck(nr, nc)) continue;
			cnt++;
		}//for
		
		return cnt < 3;
	}//iceCheck

	// 범위 체크
	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=Z || c<0 || c>=Z || map[r][c] < 1;
	}//rangeCheck

	// 먼저 격자를 2L × 2L 크기의 부분 격자로 나눈다.
	private static void stepL(int L) {
		int n = (int) Math.pow(2, L);
		int[][] tmp = copyMap();
		
		for(int i=0; i<Z; i+=n) {
			for(int j=0; j<Z; j+=n) {
				rotation(i, j, n, tmp); // 90도 회전
			}//for j
		}//for i
        
	}//stepL

	// 90도 회전
	private static void rotation(int startR, int startC, int n, int[][] tmp) {
		int maxR = startR+n;
		int maxC = startC+n;
		
		for(int r=startR, i=startC; r<maxR; r++, i++) {
			for(int c=startC, j=maxR-1; c<maxC; c++, j--) {
				map[r][c] = tmp[j][i];
			}//for c
		}//for r
        
	}//rotation

	// 원본 배열 복사
	private static int[][] copyMap() {
		int[][] tmp = new int[Z][Z];		
		for(int i=0; i<Z; i++) {
			for(int j=0; j<Z; j++) {
				tmp[i][j] = map[i][j];
			}//for i
		}//for j		
		return tmp;
	}//copyMap	

}//class