import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1445
public class Main {
	static int N, M;
	static char[][] map;
	static int[] start;
	static int[] end;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static PriorityQueue<int[]> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			char[] row = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(row[j] == 'S') start = new int[] {i, j};
				else if(row[j] == 'F') end = new int[] {i, j};
			}//for j
			map[i] = row;
		}//for i
		br.close();
		
		dijkstra();

	}//main

	private static void dijkstra() {
		boolean[][] visited = new boolean[N][M];
		int[][][] min = new int[N][M][2];
		init(visited, min);
		
		int[] cur;
		int r, c, nr, nc, gMin, gCnt;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			r = cur[0]; // 행
			c = cur[1]; // 열
			gMin = cur[2]; // 지나가는 쓰레기의 최소 개수
			gCnt = cur[3]; // 쓰레기 옆을 지나가는 칸의 개수
			
			if(visited[r][c]) continue;
			visited[r][c] = true;
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc)) continue;
				
				int ng = gCnt;
				
				if(map[nr][nc] == 'g') {
					if(min[nr][nc][0] > min[r][c][0] + 1) {
						min[nr][nc][0] = min[r][c][0] + 1;						
						if(min[nr][nc][1] > ng) min[nr][nc][1] = ng;
						pq.offer(new int[] {nr, nc, min[nr][nc][0], min[nr][nc][1]});
					}
				}else {
					if(min[nr][nc][0] > min[r][c][0]) {
						min[nr][nc][0] = min[r][c][0];						
						if(map[nr][nc] != 'F') ng += getGarbageCnt(nr, nc);
						if(min[nr][nc][1] > ng) min[nr][nc][1] = ng;
						pq.offer(new int[] {nr, nc, min[nr][nc][0], min[nr][nc][1]});
					}					
				}//else
				
			}//for
			
		}//while
		
		StringBuilder ans = new StringBuilder();
		ans.append(min[end[0]][end[1]][0]).append(" ").append(min[end[0]][end[1]][1]);
		System.out.print(ans);
	}//dijkstra

	private static void init(boolean[][] visited, int[][][] min) {
		int INF = 987654321;
		// 쓰레기로 차있는 칸을 되도록이면 적게 지나가는 것
		// 만약 되도록 적게 지나가는 경우의 수가 여러개라면, 쓰레기 옆을 지나가는 칸의 개수를 최소로
		pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[2] == o2[2]) return o1[3] - o2[3];
				return o1[2] - o2[2];
			}
		}); 

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				Arrays.fill(min[i][j], INF);
			}//for j
		}//for i
		
//		int gCnt = getGarbageCnt(start[0], start[1]);
		pq.offer(new int[] {start[0], start[1], 0, 0});
		min[start[0]][start[1]][0] = 0;
		min[start[0]][start[1]][1] = 0;
		
	}//init

	// 옆에 쓰레기 있나 체크
	private static int getGarbageCnt(int r, int c) {
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc)) continue;
			if(map[nr][nc] == 'g') return 1;
		}//for
		return 0;
	}//garbageCheck

	// 범위 체크
	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}//rangeCheck

}//class