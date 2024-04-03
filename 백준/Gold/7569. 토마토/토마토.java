import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Position{
	int z;
	int x;
	int y;
	public Position(int z, int x, int y) {
		this.z = z;
		this.x = x;
		this.y = y;
	}
}// Position

public class Main {
	static int[][][] tomato;
	static int m, n, h;
	static int[] dx = {-1, 0, 1, 0, 0, 0}; // 상하 탐색
	static int[] dy = {0, -1, 0, 1, 0, 0}; // 좌우 탐색
	static int[] dz = {0, 0, 0, 0, -1, 1}; // 위아래 탐색
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken()); // 열
		n = Integer.parseInt(st.nextToken()); // 행
		h = Integer.parseInt(st.nextToken()); // 상자 개수
		tomato = new int[h][n][m];
		Queue<Position> q = new LinkedList<>(); 
		
		for(int z=0; z<h; z++) {
			for(int x=0; x<n; x++) {
				String[] str = br.readLine().split(" ");
				for(int y=0,t=0; y<m; y++) {
					t = Integer.parseInt(str[y]);
					tomato[z][x][y] = t;
					if(t==1) q.offer(new Position(z, x, y));
				}
			}			
		}
		
		System.out.println(bfs(q));
	}//main
	
	static int bfs(Queue<Position> q) {		
		int day = 0;
		Position pos;
		while(!q.isEmpty()) {
			pos = q.poll();
			int z = pos.z;
			int x = pos.x;
			int y = pos.y;					
			
			for(int i=0; i<6; i++) {
				int nz = z + dz[i];
				int nx = x + dx[i];
				int ny = y + dy[i];

				if(nz < 0 || nx < 0 || ny < 0 || nz >= h || nx >= n || ny >= m) continue;
				if(tomato[nz][nx][ny] > 0 || tomato[nz][nx][ny] == -1) continue;

				tomato[nz][nx][ny] = tomato[z][x][y] + 1;
				q.offer(new Position(nz, nx, ny));									
			}//for
			day = tomato[z][x][y] - 1;
		}
		
		return isRipe() ? day : -1;
	}//bfs
	
	static boolean isRipe() {
		for(int[][] th : tomato) {
			for(int[] tx : th) {
				for(int t : tx) {
					if(t == 0) return false;
				}
			}
		}
		return true;
	}//isRipe
	
}// Main class