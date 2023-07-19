import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14503
// 로봇 청소기가 주어졌을 때, 청소하는 영역의 개수를 구하는 프로그램을 작성
public class Main {
	static int N, M, result; // 세로 크기 N과 가로 크기 M
	static int[][] map; // 로봇 청소기가 있는 장소
	static Robot robot; // 로봇 청소기
	static boolean flag;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 첫째 줄에 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 50)
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		// 둘째 줄에 로봇 청소기가 있는 칸의 좌표 (r, c)와 바라보는 방향 d가 주어진다.
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		robot = new Robot(r, c, d);
		
		// 셋째 줄부터 N개의 줄에 장소의 상태. 빈 칸은 0, 벽은 1로 주어진다.
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//for
		
		clean();
		System.out.print(result);
	}//main
	
	private static void clean() {
		flag = true;
		
		while(flag) {
			// 1. 현재 위치를 청소한다.
			if(!visited[robot.r][robot.c]) {
				result++;
				visited[robot.r][robot.c] = true;
			}
			// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
			move();
			
		}//while
		
	}//clean

	private static void move() {			
		int r = robot.r;
		int c = robot.c;
		int d = robot.d;
		
		// 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
		if(!check(r, c, d) ) {
			// 2A. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
			int[] rc = back(r, c, robot.d);
			r = rc[0]; c = rc[1];
			if(!rangeCheck(r, c) && map[r][c] == 0) {
				robot.init(r, c, robot.d);
				return;
			}else { // 2B. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
				flag = false;
			}			
			
		}else { // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
			d = robot.d; 
			switch (d) {
			case 0: // 북쪽
				r--;
				break;
			case 1: // 동쪽
				c++;
				break;
			case 2: // 남쪽
				r++;
				break;
			case 3: // 서쪽
				c--;
				break;
			}//switch
		}//else
	
		// 3A. 반시계 방향으로 90 회전한다.
		robot.d = d;
		// 3B. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
		if(!rangeCheck(r, c) && !visited[r][c]) robot.init(r, c, d);
		// 3C. 1번으로 돌아간다.
	}//move
	
	// 후진
	private static int[] back(int r, int c, int d) {
		
		switch (d) {
		case 0: // 북쪽
			r++;
			break;
		case 1: // 동쪽
			c--;
			break;
		case 2: // 남쪽
			r--;
			break;
		case 3: // 서쪽
			c++;
			break;
		}//switch
		
		return new int[] {r, c};
	}//back

	private static boolean check(int r, int c, int d) {
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		int dir = d-1; 
		if(dir==-1)dir = 3; 
		for(int i=0; i<4; i++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if(rangeCheck(nr, nc)||visited[nr][nc] || map[nr][nc] == 1) {
				dir--; 
				if(dir==-1)dir = 3; 
				continue;
			}
			robot.d = dir;
			return true;
		}
		return false;
	}//

	private static boolean rangeCheck(int r, int c) {
		
		return (r < 0 || r >= N || c < 0 || c >= M );
	}//

	
	static class Robot{
		int r; // 행
		int c; // 열
		// 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽
		int d; // 바라보는 방향 d
		
		public void init(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
		public Robot(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
		@Override
		public String toString() {
			return "Robot [r=" + r + ", c=" + c + ", d=" + d + "]";
		}		
	}//Robot

}//class

