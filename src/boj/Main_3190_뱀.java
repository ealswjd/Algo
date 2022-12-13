package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3190
// 게임이 몇 초에 끝나는지 출력한다.
public class Main_3190_뱀 {
	static int N, K, L; // 보드의 크기 N, 사과의 개수 K, 뱀의 방향 변환 횟수 L
	static int[][] map; // NxN 정사각 보드
	static Snake[] snakeArr; // 뱀의 방향 변환 정보
	static PriorityQueue<Snake> q;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 첫째 줄에 보드의 크기 N이 주어진다. (2 ≤ N ≤ 100)
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		// 다음 줄에 사과의 개수 K가 주어진다. (0 ≤ K ≤ 100)
		K = Integer.parseInt(br.readLine());		
		
		// 다음 K개의 줄에는 사과의 위치가 주어지는데, 첫 번째 정수는 행, 두 번째 정수는 열 위치를 의미
		StringTokenizer st;
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) -1;
			int c = Integer.parseInt(st.nextToken()) -1;
			map[r][c] = 1;
			
		}
		
		// 다음 줄에는 뱀의 방향 변환 횟수 L 이 주어진다. (1 ≤ L ≤ 100)
		L = Integer.parseInt(br.readLine());
		// 다음 L개의 줄에는 뱀의 방향 변환 정보가 주어지는데,  정수 X와 문자 C로 이루어져 있
		q = new PriorityQueue<>();
		snakeArr = new Snake[L];
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int c = st.nextToken().charAt(0);
			snakeArr[i] = new Snake(x, c);
			q.offer(new Snake(x, c));
		}
		
		int second = game(); // 게임이 몇 초에 끝나는지
		System.out.print(second);
	}//main
	
	private static int game() {
		// 뱀은 처음에 오른쪽을 향한다. 뱀은 매 초마다 이동
		int c = 'D', d = 1;
		int row = 0;
		int col = 0;
		map[row][col] = -1;
		int second=0;
		
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {0, 0});
		
		// 상하좌우
		int[] dd = {0, 4, 3, 1, 2};
		int[] dl = {0, 3, 4, 2, 1};
		// 사과를 먹으면 뱀 길이가 늘어난다.
		// 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
		// X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다.
		Snake snake=new Snake(second, c);
		q.offer(snake);
		while(true) {
			if(!q.isEmpty() && q.peek().x == second) {
				snake = q.poll();
				c = snake.c;				
				if(c=='D') {
					d = dd[d];
				}else {
					d = dl[d];
				}
			}
			// 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
			row = dq.peekFirst()[0];
			col = dq.peekFirst()[1];
			int nr = row;
			int nc = col;
			if(d==1) nr--; 
			else if(d==2) nr++;
			else if(d==3) nc--;
			else nc++;
			
			second++;
			if(check(nr, nc) || map[nr][nc] == -1) break;
			// 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
			if(map[nr][nc] != 1) {
				int[] tail = dq.pollLast();
				map[tail[0]][tail[1]] = 0;			
			}
			// 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
			dq.offerFirst(new int[] {nr, nc});
			map[nr][nc] = -1;

		}//while
		
		return second;
	}//game

	private static boolean check(int r, int c) {
		return r<0 || r>=N || c<0 || c>=N;
	}

	static class Snake implements Comparable<Snake>{
		int x; // X초
		int c; // 회전방향 - 왼쪽(C가 'L') 또는 오른쪽(C가 'D')
		public Snake(int x, int c) {
			super();
			this.x = x;
			this.c = c;
		}
		@Override
		public int compareTo(Snake o) {
			return this.x - o.x;
		}	
		
	}//Snake

}//class

/*

6
3
3 4
2 5
5 3
3
3 D
15 L
17 D

---> 9

*/
