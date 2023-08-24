import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static int W, H;
	static int[] start;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		while(tc-->0) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			Queue<int[]> q = new LinkedList<>();
			map = new char[H+2][W+2];
			visited = new boolean[H+2][W+2];
			for(int i=1; i<=H; i++) {
				char[] tmp = br.readLine().toCharArray();
				for(int j=1; j<=W; j++) {
					map[i][j] = tmp[j-1];
					if(map[i][j] == '*') {
						q.offer(new int[] {i, j, 0, 0});
						visited[i][j] = true;
					}
					else if(map[i][j] == '@') {
						start = new int[] {i, j, 1, 0};
						visited[i][j] = true;
					}
				}
			}
			
			q.offer(start);
			ans.append( bfs(q) );
			ans.append("\n");
		}//while

		System.out.print(ans);
	}//main

	
//	'.': 빈 공간
//	'#': 벽
//	'@': 상근이의 시작 위치
//	'*': 불
	private static String bfs(Queue<int[]> q) {
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		int R = H+1;
		int C = W+1;
		
		int r, c, s, time;
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			r = tmp[0];
			c = tmp[1];
			s = tmp[2];
			time = tmp[3];
			
			if(s==1 && (r==0 || r==R || c==0 || c==C)) 
				return String.valueOf(time);
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length || visited[nr][nc]) continue;
				if(map[nr][nc] == '#'
						|| ( s==0 && (nr==0 || nr==R || nc==0 || nc==C)) ) continue;
				
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc, s, time+1});
				
			}//for
		}//while
		
		return "IMPOSSIBLE";
	}//bfs


}//class