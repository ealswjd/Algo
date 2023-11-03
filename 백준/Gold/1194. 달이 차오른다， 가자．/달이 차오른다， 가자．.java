import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1194
public class Main {
	static int R, C;
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());		
		
		map = new char[R][C];
		int sr=0, sc=0;
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j] == '0') {
					sr = i;
					sc = j;
				}//if
			}//for j
		}//for i
		br.close();
		
		int result = bfs(sr, sc);
		System.out.print(result);
	}//main

	private static int bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c, 0, 0}); // 시작 위치
		boolean[][][] visited = new boolean[R][C][1<<7];
		visited[r][c][0] = true;
		map[r][c] = '.';
		
		int[] cur;
		int key, cnt;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			key = cur[2];
			cnt = cur[3];
			
			if(map[r][c] == '1') return cnt;
			
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(rangeCheck(nr, nc)) continue;
				if(map[nr][nc]=='#' || visited[nr][nc][key]) continue;
				
				if(map[nr][nc]>='a' && map[nr][nc]<='z') { // 열쇠
					int nk = key | (1 << (map[nr][nc]-'a'));
					visited[nr][nc][nk] = true;
					q.offer(new int[] {nr, nc, nk, cnt+1});
				}
				else if(map[nr][nc]>='A' && map[nr][nc]<='Z') { // 문
					boolean flag = (key & (1 << (map[nr][nc]-'A'))) != 0;
					if(flag) { // 키 있음
						visited[nr][nc][key] = true;
						q.offer(new int[] {nr, nc, key, cnt+1});
					}//if
				}
				else {
					visited[nr][nc][key] = true;
					q.offer(new int[] {nr, nc, key, cnt+1});
				}
			}//for
			
		}//while
		
		return -1;
	}//bfs

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=R || c<0 || c>=C;
	}//rangeCheck

}//class