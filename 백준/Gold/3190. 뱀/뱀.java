import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3190
public class Main {
	static final int APPLE=2, SNAKE=1, L='L', R='D';
	static int N;
	static int[][] map;
	static Deque<int[]> snake;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 보드의 크기
		int K = Integer.parseInt(br.readLine()); // 사과의 개수
		map = new int[N][N]; // 보드
		// 게임이 시작할때 뱀은 맨위 맨좌측에 위치
		snake = new ArrayDeque<>();
		snake.add(new int[] {0, 0});
		
		StringTokenizer st;
		// 사과
		while(K-->0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) -1;
			int c = Integer.parseInt(st.nextToken()) -1;
			map[r][c] = APPLE;
		}//while
		
		// 뱀의 방향 변환 횟수 (1 ≤ cnt ≤ 100)
		int cnt = Integer.parseInt(br.readLine());
		// 뱀의 방향 변환 정보 주어짐. 정수 X와 문자 C로 이루어져 있음
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, R}); // 뱀은 처음에 오른쪽을 향한다.
		while(cnt-->0) {
			st = new StringTokenizer(br.readLine());
			// X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻
			int x = Integer.parseInt(st.nextToken());
			int c = st.nextToken().charAt(0);
			q.offer(new int[] {x, c});
		}//while
		br.close();
		
		int second = Dummy(q); // 게임이 몇 초에 끝나는지
		System.out.print(second);
	}//main

	private static int Dummy(Queue<int[]> q) {
		int second=0;
		// 상하좌우
		int[] dR = {0, 4, 3, 1, 2}; // 오른쪽
		int[] dL = {0, 3, 4, 2, 1}; // 왼쪽
		int r = 0, c = 0, nr, nc;
		int dir=R, dNum = 1;
		
		int[] cur;
		while(true) {
			if(!q.isEmpty() && q.peek()[0] == second) {
				cur = q.poll();
				dir = cur[1];
				if(dir == R) dNum = dR[dNum];
				else dNum = dL[dNum];
			}//if
			
			r = snake.peekFirst()[0];
			c = snake.peekFirst()[1];
			nr = r;
			nc = c;
			if(dNum == 1) nr--;
			else if(dNum == 2) nr++;
			else if(dNum == 3) nc--;
			else nc++;
			
			second++;
			// 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
			if(isEnd(nr, nc)) break;
			
			// 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다.
			if(map[nr][nc] != APPLE) {
				int[] tail = snake.pollLast();
				map[tail[0]][tail[1]] = 0;
			}//if
			
			// 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
			snake.addFirst(new int[] {nr, nc});
			map[nr][nc] = SNAKE;			
		}//while
		
		return second;
	}//Dummy

	// 게임 종료 조건 체크 (벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.)
	private static boolean isEnd(int r, int c) {
		return r<0 || r>=N || c<0 || c>=N || map[r][c] == SNAKE;
	}//isEnd

}//class