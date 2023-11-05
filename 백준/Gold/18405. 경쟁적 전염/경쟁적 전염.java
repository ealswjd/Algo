import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18405
public class Main {
	static int N, K, S, X, Y;
	static int[][] map; // NxN 크기의 시험관
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 시험관 크기
		K = Integer.parseInt(st.nextToken()); // 바이러스 종류
		
		map = new int[N][N];
		PriorityQueue<Virus> pq = new PriorityQueue<>();
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] > 0) {
					pq.offer(new Virus(map[r][c], r, c, 0));
				}//if
			}//for c
		}//for r
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()); // S초
		X = Integer.parseInt(st.nextToken()) - 1; // 목표 행
		Y = Integer.parseInt(st.nextToken()) - 1; // 목표 열
		br.close();
		
		int virus = bfs(pq);
		System.out.print(virus);
	}//main

	private static int bfs(PriorityQueue<Virus> pq) {
		int num, r, c, nr, nc, time;
		Virus cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			num = cur.num;
			r = cur.r;
			c = cur.c;
			time = cur.time;
			if(time>=S) return map[X][Y];
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc)) continue;
				map[nr][nc] = num;
				pq.offer(new Virus(num, nr, nc, time+1));
			}//for
		}//while
		
		return map[X][Y];
	}//bfs

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=N || c<0 || c>=N || map[r][c]>0;
	}//rangeCheck

	static class Virus implements Comparable<Virus> {
		int num; // 바이러스 번호
		int r; // 행
		int c; // 열
		int time; // 시간
		public Virus(int num, int r, int c, int time) {
			this.num = num;
			this.r = r;
			this.c = c;
			this.time = time;
		}
		@Override
		public int compareTo(Virus v) {
			if(this.time == v.time) return this.num - v.num;
			return this.time - v.time;
		}
	}//Virus
}//class