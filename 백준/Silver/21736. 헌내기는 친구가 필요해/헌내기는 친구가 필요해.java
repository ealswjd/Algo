import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21736
public class Main {
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		
		int r=0,c=0;
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'I') {
					r = i;
					c = j;
				}//if
			}//for j
		}//for i
		br.close();
		
		int cnt = bfs(r, c);
		
		if(cnt == 0) System.out.print("TT");
		else System.out.print(cnt);

	}//main

	private static int bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		
		int[] cur;
		int cnt = 0;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(rangeCheck(nr, nc) || visited[nr][nc] || map[nr][nc]=='X') continue;
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc});
				if(map[nr][nc] == 'P') cnt++;				
			}//for
		}//while
		
		return cnt;
	}//bfs

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=N || c<0 || c>=M;
	}//rangeCheck

}//class