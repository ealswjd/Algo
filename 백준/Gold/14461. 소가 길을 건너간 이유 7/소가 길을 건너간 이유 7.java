import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14461
public class Main {
	static int N, T;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		br.close();

		int end = N-1;
		int time = getTime(end);
		System.out.print(time);
	}//main

	private static int getTime(int end) {
		PriorityQueue<Grass> pq = new PriorityQueue<>();
		pq.offer(new Grass(0, 0, 0, 0));
		
		Grass cur;
		int r, c, cnt, cost;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			r = cur.r; // 행
			c = cur.c; // 열
			cnt = cur.cnt; // 이동 횟수
			cost = cur.cost; // 총 시간
			
			if(visited[r][c][cnt]) continue;
			visited[r][c][cnt] = true;
			
			if(r == end && c == end) return cost;			
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(rangeCheck(nr, nc) || visited[nr][nc][(cnt+1)%3]) continue;
				if(cnt == 2) pq.offer(new Grass(nr, nc, 0, cost + map[nr][nc] + T));
				else pq.offer(new Grass(nr, nc, cnt+1, cost + T));				
			}//for
			
		}//while
		
		return 0;
	}//getTime

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;
	}//rangeCheck

	private static void init() {
		map = new int[N][N];
		visited = new boolean[N][N][3];		
	}//init
	
	static class Grass implements Comparable<Grass> {
		int r; // 행
		int c; // 열
		int cnt; // 이동 횟수
		int cost; // 총 시간
		public Grass(int r, int c, int cnt, int cost) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.cost = cost;
		}
		@Override
		public int compareTo(Grass g) {
			return this.cost - g.cost;
		}
	}//Grass

}//class