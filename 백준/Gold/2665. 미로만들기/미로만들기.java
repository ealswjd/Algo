import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/2665
public class Main {
	static int N;
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static PriorityQueue<int[]> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}//for
		br.close();
		
		System.out.print(makeMaze());
	}//main

	// 미로 만들기
	private static int makeMaze() {
		int[][][] min = new int[N][N][1];
		boolean[][] visited = new boolean[N][N];
		init(min);
		pq.offer(new int[] {0, 0, 0});
		
		int r, c, nr, nc, cnt=0;
		int[] cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			r = cur[0];
			c = cur[1];
			cnt = cur[2];
			
			if(visited[r][c]) continue;
			visited[r][c] = true;
			
			if(r == N-1 && c == N-1) return cnt;
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc)) continue;
				
				if(map[nr][nc] == '0') { // 0은 검은 방 (벽)
					if(min[nr][nc][0] > cnt + 1) {
						min[nr][nc][0] = cnt + 1;
						pq.offer(new int[] {nr, nc, min[nr][nc][0]});
					}
				}else { // 1은 흰 방 (빈 공간)
					if(min[nr][nc][0] > cnt) {
						min[nr][nc][0] = cnt;
						pq.offer(new int[] {nr, nc, min[nr][nc][0]});
					}
				}
			}//for
		}//while
		
		return cnt;
	}//makeMaze

	// 범위 체크
	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;
	}//rangeCheck

	// 초기화
	private static void init(int[][][] min) {
		int INF = 987654321;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				Arrays.fill(min[i][j], INF);
			}//for j
		}//for i
		
		min[0][0][0] = 0;
		
		pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
	}//init

}//class