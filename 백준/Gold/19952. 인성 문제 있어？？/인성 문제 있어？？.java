import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19952
public class Main {
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	static int H, W, O, F, sr, sc, er, ec;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int r, c, h;
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			init(st);
			while(O-->0) {
				st = new StringTokenizer(br.readLine());
				r = Integer.parseInt(st.nextToken()) - 1;
				c = Integer.parseInt(st.nextToken()) - 1;
				h = Integer.parseInt(st.nextToken());
				map[r][c] = h;
			}//while
			
			ans.append(bfs());
		}//while
		
		System.out.print(ans);
	}//main

	private static String bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {sr, sc, F});
		visited[sr][sc] = true;
		
		int r, c, f, nr, nc, nf;
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			f = cur[2];
			if(r==er && c==ec) return "잘했어!!\n";
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(f <= 0 || check(nr, nc) || visited[nr][nc]) continue;
				if(map[nr][nc] > map[r][c] && map[nr][nc] - map[r][c] > f) continue;					

				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc, f-1});
			}//for
		}//while
		
		return "인성 문제있어??\n";
	}//bfs

	private static boolean check(int r, int c) {
		return r<0 || r>=H || c<0 || c>=W;
	}//check

	private static void init(StringTokenizer st) {
		H = Integer.parseInt(st.nextToken()); // 세로길이
		W = Integer.parseInt(st.nextToken()); // 가로길이
		O = Integer.parseInt(st.nextToken()); // 장애물의 개수
		F = Integer.parseInt(st.nextToken()); // 초기 힘
		sr = Integer.parseInt(st.nextToken()) - 1; // 출발지의 좌표 정보 - 행
		sc = Integer.parseInt(st.nextToken()) - 1; // 출발지의 좌표 정보 - 열
		er = Integer.parseInt(st.nextToken()) - 1; // 목적지의 좌표정보 - 행
		ec = Integer.parseInt(st.nextToken()) - 1; // 목적지의 좌표정보 - 열
		
		map = new int[H][W];
		visited = new boolean[H][W];
	}//init

}//class