import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos{
	int x, y;
	int cnt; // 이동거리
	int wall; // 벽을 부쉈는지
	public Pos(int x, int y, int cnt, int wall){
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.wall = wall;
	}
}

class Main {	
	private static int[][] map; // 지도
	private static boolean[][][] visited; // 방문여부
	private static int n,m;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m][2]; 
		
		for(int i=0; i<n; i++) {
			String[] str = br.readLine().split("");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}	
			
		br.close();
		System.out.println(bfs(0,0));
	}//main
	
	static int bfs(int x, int y) {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(x, y, 1, 0));
		visited[x][y][0] = true; 
		visited[x][y][1] = true; 
		
		int[] dx = {-1, 1, 0, 0}; // 상하
		int[] dy = {0, 0, -1, 1}; // 좌우

		while(!q.isEmpty()) {
			Pos pos = q.poll();
			x = pos.x;
			y = pos.y;
			
			if(x == n-1 && y == m-1) return pos.cnt; 
			for(int i=0; i<4; i++) { // 상하좌우 탐색
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue; // 범위 확인
				if(map[nx][ny] == 0) { // 벽이 아닐 때
					if(!visited[nx][ny][pos.wall]) { 
						q.offer(new Pos(nx, ny, pos.cnt + 1, pos.wall));
						visited[nx][ny][pos.wall] = true;
					}
				}else { // 벽일 때
					if(pos.wall == 0 && !visited[nx][ny][1]) { 
						q.offer(new Pos(nx, ny, pos.cnt + 1, 1));
						visited[nx][ny][1] = true;
					}
				}								
			}//for
		}//while
		
		return -1;
	}//bfs
	
	
}// Main class