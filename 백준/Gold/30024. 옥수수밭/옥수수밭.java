import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/30024
public class Main {
	static int R, C;
	static int[][] map;
	static boolean[][] visited;
	static PriorityQueue<int[]> pq;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 옥수수밭 행
		C = Integer.parseInt(st.nextToken()); // 옥수수밭 열
		map = new int[R][C]; // 옥수수밭
		visited = new boolean[R][C];
		pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(i==0 || i==R-1 || j==0 || j==C-1) {
					pq.offer(new int[] {map[i][j], i, j});
					visited[i][j] = true;					
				}//if
			}//for
		}//for
		int K = Integer.parseInt(br.readLine()); // 수확 목표
		br.close();
		
		getPosition(K);
	}//main

	private static void getPosition(int K) {
		StringBuilder ans = new StringBuilder();
		int[] cur;
		int r, c;
		
		while(K-->0) {
			cur = pq.poll();
			r = cur[1];
			c = cur[2];
			ans.append(r+1).append(" ").append(c+1).append("\n");
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				pq.offer(new int[] {map[nr][nc], nr, nc});
			}//for
		}//while
		
		System.out.print(ans);
	}//getPosition

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=R || c<0 || c>=C;
	}//rangeCheck

}//class