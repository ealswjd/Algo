import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2638
public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		int cheeseCnt = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) cheeseCnt++; // 치즈면 개수 증가
			}//for j
		}//for i
		br.close();
		
		int time = getTime(cheeseCnt);		
		System.out.print(time);
	}//main
	
	private static int getTime(int cheeseCnt) {
		HashMap<Position, Integer> cheeseMap = new HashMap<>();
		Queue<Position> q = new LinkedList<>();
		dfs(q, 0, 0, 0);

		Position cur;
		int r, c, nr, nc, time=0, total=0;
		while(!q.isEmpty()) {
			int size = q.size();
			total++; // 치즈가 전부 녹아 없어지는 시간
			while(size-->0) {
				cur = q.poll();
				r = cur.r; // 행
				c = cur.c; // 열
				time = cur.time; // 시간
				
				for(int i=0; i<4; i++) {
					nr = r + dr[i];
					nc = c + dc[i];
					if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
					
					if(map[nr][nc]==0) dfs(q, nr, nc, time); // 공기
					else { // 치즈
						Position cheese = new Position(nr, nc, time+1);
						cheeseMap.put(cheese, cheeseMap.getOrDefault(cheese, 0) + 1);
						if(cheeseMap.get(cheese) > 1) { // 2변 이상 
							dfs(q, cheese.r, cheese.c, cheese.time); // 공기 확보
							cheeseCnt--; // 치즈 개수 감소						
						}//if
					}//else
				}//for				
			}//while

			if(cheeseCnt == 0) break; // 치즈 다 녹으면 그만
		}//while
		
		return total; 
	}//getTime

	private static void dfs(Queue<Position> q, int r, int c, int time) {
		visited[r][c] = true;
		q.offer(new Position(r, c, time));
		map[r][c] = -1;
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
			if(map[nr][nc] > 0) continue;
			dfs(q, nr, nc, time);
		}//for
		
	}//dfs

	// 범위 체크
	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=N || c<0 || c>=M;
	}//rangeCheck

	static class Position {
		int r;
		int c;
		int time;
		public Position(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Position other = (Position) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}
	}//Cheese

}//class