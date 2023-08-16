import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14940
public class Main {
	static int N, M;
	static int[][] map;
	static int[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static Queue<int[]> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		
		for(int i=0; i<N; i++) { Arrays.fill(visited[i], -1); }
		
		q = new LinkedList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if(map[i][j] == 0) visited[i][j] = 0;
				else if(map[i][j] == 2) {
					q.offer(new int[] {i, j});
					visited[i][j] = 0;
				}
			}//for j
		}//for i
		br.close();
		
		bfs();
		print();
	}//main

	private static void bfs() {
		int r, c, nr, nc;
		int[] cur;
		
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc) || map[nr][nc] == 0) continue;
				if(visited[nr][nc] != -1 && visited[nr][nc] <= visited[r][c]+1) continue;
				visited[nr][nc] = visited[r][c] + 1;
				q.offer(new int[] {nr, nc});
			}//for
		}//while
		
	}//bfs

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || c < 0 || r >= N || c >= M;
	}//rangeCheck

	private static void print() {
		StringBuilder ans = new StringBuilder();
		for(int[] r : visited) {
			for(int n : r) {
				ans.append(n).append(" ");
			}//for n
			ans.append("\n");
		}//for r
		System.out.print(ans);
	}//print

}//class