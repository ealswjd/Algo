import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16441
public class Main {
	static final char LAND='.', ICE='+', MOUNTAIN='#', WOLF='W';
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == WOLF) {
					q.offer(new int[] {i, j});
					visited[i][j] = true;
				}//for
			}//j
		}//i
		br.close();

		bfs(q);
		print();
	}//main

	private static void print() {
		StringBuilder ans = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j] && map[i][j] == LAND) map[i][j] = 'P';
				ans.append(map[i][j]);
			}//j
			ans.append('\n');
		}//i
		
		System.out.print(ans);
	}//print

	// 늑대는 상하좌우 인접한 칸 중 산이 아닌 칸으로 이동할 수 있습니다.
	// 만약 이동한 칸이 빙판이라면 초원을 밟거나 산에 부딪칠 때까지 이동한 방향으로 미끄러집니다.
	// 산에 부딪친 경우 늑대는 빙판 위에 가만히 서있을 수 있고 다시 다른 방향으로 이동할 수 있습니다.
	private static void bfs(Queue<int[]> q) {
		int r, c, nr, nc;
		int[] cur;
		
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
				if(map[nr][nc] == LAND) { // 초원
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}else {
					while(!rangeCheck(nr, nc) && map[nr][nc] == ICE) {
						nr += dr[i];
						nc += dc[i];
					}//while
					if(rangeCheck(nr, nc)) {
						nr -= dr[i];
						nc -= dc[i];
					}
					if(!visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}//else
			}//for
		}//while
		
	}//bfs

	private static boolean rangeCheck(int r, int c) {
		return r<=0 || r>=N-1 || c<=0 || c>=M-1 || map[r][c] == MOUNTAIN;
	}//rangeCheck

}//class