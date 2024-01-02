import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21608
public class Main {
	static int N;
	static int[][] map;
	static boolean[][] likes;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int len = N*N;
		likes = new boolean[len+1][len+1];
		int[] order = new int[len];
		StringTokenizer st;
		
		for(int i=0; i<len; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 학생 번호
			// 학생이 좋아하는 학생 4명의 번호
			for(int j=0; j<4; j++) {
				likes[num][Integer.parseInt(st.nextToken())] = true;
			}//for j
			
			order[i] = num;
		}//for i
		br.close();

		int score = getScore(order);
		System.out.print(score);
	}//main
	
	private static int getScore(int[] order) {
		PriorityQueue<Blank> pq = new PriorityQueue<>();
		int r=1, c=1;
		int len = N * N;
		map[r][c] = order[0];
		
		Blank blank;
		int cur;
		for(int i=1; i<len; i++) {
			cur = order[i];
			getBlank(cur, pq);
			blank = pq.poll();
			map[blank.r][blank.c] = cur;
		}//for
		
		int sum = 0;
		int nr, nc, likeCnt = 0;
		int[] score = {0, 1, 10, 100, 1000};
		for(r=0; r<N; r++) {
			for(c=0; c<N; c++) {
				likeCnt = 0;
				cur = map[r][c];
				for(int i=0; i<4; i++) {
					nr = r + dr[i];
					nc = c + dc[i];
					if(rangeCheck(nr, nc)) continue;
					if(likes[cur][map[nr][nc]]) likeCnt++;
				}//for
				sum += score[likeCnt];
			}//for c
		}//for r
		
		return sum;
	}//getScore

	private static void getBlank(int cur, PriorityQueue<Blank> pq) {		
		int emptyCnt, likeCnt;
		int nr, nc;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c] != 0) continue;
				emptyCnt = likeCnt = 0;
				for(int i=0; i<4; i++) {
					nr = r + dr[i];
					nc = c + dc[i];
					if(rangeCheck(nr, nc)) continue;
					if(map[nr][nc] == 0) emptyCnt++;
					else if(likes[cur][map[nr][nc]]) likeCnt++;
				}//for

				Blank tmp = new Blank(r, c, emptyCnt, likeCnt);
				if(pq.isEmpty()) pq.offer(tmp);
				else {
					if(pq.peek().compareTo(tmp) >= 0) {
						pq.poll();
						pq.offer(tmp);
					}//if
				}//else
				
			}//for c
		}//for r
		
	}//getBlank

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=N || c<0 || c>=N;
	}//rangeCheck

	static class Blank implements Comparable<Blank> {
		int r;
		int c;
		int emptyCnt;
		int likeCnt;
		public Blank(int r, int c, int emptyCnt, int likeCnt) {
			this.r = r;
			this.c = c;
			this.emptyCnt = emptyCnt;
			this.likeCnt = likeCnt;
		}
		
		@Override
		public int compareTo(Blank o) {
			if(this.likeCnt == o.likeCnt) {
				// 비어있는 칸이 가장 많은 칸
				if(this.emptyCnt == o.emptyCnt) {
					// 행의 번호가 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 작은 칸
					if(this.r == o.r) return this.c - o.c;
					return this.r - o.r;
				}
				return o.emptyCnt - this.emptyCnt;
			}
			// 좋아하는 학생이 인접한 칸에 가장 많은 칸
			return o.likeCnt - this.likeCnt;
		}
	}//Blank

}//class