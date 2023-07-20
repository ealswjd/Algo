import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, size=2; // 상어 사이즈
	static int[][] map, distance;
	static Position sPos;
	static PriorityQueue<Position> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		distance = new int[N][N];		
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					sPos = new Position(i, j, 0);
					map[i][j] = 0;
				}
			}
		}

		br.close();
		
		int result = 0;
		int cnt = 0;
		while(true) {
			Position fish = getFish();
			if(fish == null) break;
			int fx = fish.x;
			int fy = fish.y;
			int fd = fish.d-1;
			map[fx][fy] = 0;
			cnt++;
			sPos.x = fx;
			sPos.y = fy;
			result += fd;
			if(cnt == size) {
				size++;
				cnt = 0;
			}
		}
		
		System.out.print(result);
	}//main

	private static Position getFish() {
		PriorityQueue<Position> pq = new PriorityQueue<>();
		Queue<Position> q = new LinkedList<>();
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int[][] d = new int[N][N];
		int x = sPos.x;
		int y = sPos.y;
		d[x][y] = 1;
		q.offer(new Position(x, y));
		
		Position pos;
		while(!q.isEmpty()) {
			pos = q.poll();
			x = pos.x;
			y = pos.y;
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if(d[nx][ny] != 0 || map[nx][ny] > size) continue;
				q.offer(new Position(nx, ny));
				d[nx][ny] = d[x][y] + 1;
				if(map[nx][ny] < size && map[nx][ny] != 0) 
					pq.offer(new Position(nx, ny, d[nx][ny]));
			}
		}
		if(pq.size() > 0) return pq.poll();
		return null;
	}


}//class

class Position implements Comparable<Position> {
	int x;
	int y;
	int d;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;	
	}
	public Position(int x, int y, int d) {
		this(x, y);
		this.d = d;		
	}
	@Override
	public int compareTo(Position o) {
		return this.d == o.d ? (this.x==o.x ? this.y-o.y :this.x-o.x) : this.d- o.d;
	}	
}