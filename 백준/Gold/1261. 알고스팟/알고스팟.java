import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1261
public class Main {
	static int N, M;
	static char[][] map; // 지도
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] min;
	static boolean[][] visited;
	static PriorityQueue<int[]> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		init();
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}//for
		br.close();
		
		
		int cnt = getCnt();
		System.out.print(cnt);
	}//main

	// 초기화
	private static void init() {
		map = new char[N][M];
		min = new int[N][M];
		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			Arrays.fill(min[i], 987654321);
		}//for
		min[0][0] = 0;
		
		pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
	}//init

	private static int getCnt() {
		pq.offer(new int[] {0, 0, 0});

		int r, c, nr, nc, cnt=0;
		int[] cur;
		
		while(!pq.isEmpty()) {
			cur = pq.poll();
			r = cur[0]; // 행
			c = cur[1]; // 열
			cnt = cur[2]; // 부순 벽 개수
			
			if(r == N-1 && c == M-1) return cnt; // 목적지 도착
			if(visited[r][c]) continue; // 방문 여부 체크
			
			visited[r][c] = true;
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc)) continue;
				if(map[nr][nc] == '1') { // 벽
					if(min[nr][nc] > cnt + 1) { // 벽 부수기
						min[nr][nc] = cnt + 1;
						pq.offer(new int[] {nr, nc, min[nr][nc]});
					}//if					
				}else { // 빈 방
					if(min[nr][nc] > cnt) { 
						min[nr][nc] = cnt;
						pq.offer(new int[] {nr, nc, min[nr][nc]});
					}//if
				}//else
			}//for
		}//while
		
		return cnt;
	}//getCnt

	// 범위 체크
	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}//rangeCheck

}//class