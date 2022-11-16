package sewa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 5653. [모의 SW 역량테스트] 줄기세포배양
public class Solution_5653_줄기세포배양 {
	static int K, R, C;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			R = N+K*2+4;
			C = M+K*2+4;
			map = new int[R][C];
			visited = new boolean[R][C];
			
			PriorityQueue<Cell> pq = new PriorityQueue<>();
			for(int i=R/2-1; i<R/2-1+N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=C/2-1; j<C/2-1+M; j++) {
					int power = Integer.parseInt(st.nextToken());
					if(power!=0) {
						map[i][j] = power;
						visited[i][j] = true;
						pq.offer(new Cell(i, j, power, power*2));
					}
				}
			}
			
			bfs(pq);
			
			sb.append(pq.size());
			sb.append("\n");
		}//for tc

		System.out.print(sb);
	}//main

	private static void bfs(PriorityQueue<Cell> pq) {
		Queue<Cell> tmp = new LinkedList<>();
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		Cell cell;
		for(int time=1; time<=K; time++) {
			while(!pq.isEmpty()) {
				cell = pq.poll();
				int r = cell.r;
				int c = cell.c;
				cell.curPower = cell.curPower-1;
				if(cell.power > cell.curPower) {
					for(int i=0; i<4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						if(nr<0 || nr>=R || nc<0 || nc>=C) continue;
						if(!visited[nr][nc]) {
							visited[nr][nc] = true;
							tmp.offer(new Cell(nr, nc, cell.power, cell.power*2));					
						}
					}
					
				}
				if(cell.curPower != 0) {
					tmp.offer(new Cell(cell.r, cell.c, cell.power, cell.curPower));
				}
			}//while
			
			while(!tmp.isEmpty()) {
				pq.offer(tmp.poll());
			}
			
		}//for
		
	}//bfs

	static class Cell implements Comparable<Cell>{
		int r;
		int c;
		int power;
		int curPower;
		
		public Cell(int r, int c, int power, int curPower) {
			super();
			this.r = r;
			this.c = c;
			this.power = power;
			this.curPower = curPower;
		}

		@Override
		public String toString() {
			return "Cell [r=" + r + ", c=" + c + ", power=" + power + ", curPower=" + curPower + "]";
		}

		@Override
		public int compareTo(Cell o) {
			return o.power - this.power;
		}		
	}//Cell
}//class
