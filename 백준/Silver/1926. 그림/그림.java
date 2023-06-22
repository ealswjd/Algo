import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

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
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//for
		br.close();
		
		int cnt = 0;
		int max = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					max = Math.max(max, bfs(i, j));
					cnt++;
				}
			}//for j
		}//for i
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		sb.append(max);
		
		System.out.print(sb);

	}//main

	private static int bfs(int r, int c) {
		int max = 1;
		Queue<int[]> q = new LinkedList<>();
		visited[r][c] = true;
		q.offer(new int[] {r, c});
		
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(!check(nr, nc)) continue;
				visited[nr][nc] = true;
				max++;
				q.offer(new int[] {nr, nc});
			}
			
		}//while
		
		return max;
	}//bfs

	private static boolean check(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<M && map[r][c]==1 && !visited[r][c]);
	}//check

}//class

/*

6 5
1 1 0 1 1
0 1 1 0 0
0 0 0 0 0
1 0 1 1 1
0 0 1 1 1
0 0 1 1 1

*/