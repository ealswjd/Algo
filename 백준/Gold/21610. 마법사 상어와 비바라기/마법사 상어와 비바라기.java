import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21610
public class Main {
	static int N, M, sum;
	static int[][] map;
	static boolean[][] remove;
	static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1}; // 행 이동
	static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1}; // 열 이동
	static int[] ddr = {-1, -1, 1, 1}; // 대각선 행 이동
	static int[] ddc = {-1, 1, -1, 1}; // 대각선 열 이동
	static Queue<Node> cloud;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자판 크기
		M = Integer.parseInt(st.nextToken()); // M번의 명령
		
		sum = 0;
		map = new int[N][N]; 		// N*N 격자판
		
		// N×N인 격자 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sum += map[i][j];
			}
		}//for
		
		// (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다.
		int[] startR = {N-1, N-1, N-2, N-2};
		int[] startC = {0, 1, 0, 1};
		cloud = new LinkedList<>();
		for(int i=0; i<4; i++) {
			cloud.offer(new Node(startR[i], startC[i]));	
		}		

		int d, s;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken()); // 방향
			s = Integer.parseInt(st.nextToken()); // 칸 이동
			remove = new boolean[N][N]; // 구름이 삭제 여부
			move(d, s);
			// 5. 물 2 이상인 모든 칸에 구름이 생기고, 물 -2 
			makeCloud();
		}//while
		br.close();

		System.out.print(sum);
	}//main
	
	private static void makeCloud() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c] < 2 || remove[r][c]) continue;
				map[r][c] -= 2;
				sum -= 2;
				cloud.offer(new Node(r, c));
			}
		}//for
	}//makeCloud

	private static void move(int d, int s) {
		Node basket;
		int size = cloud.size();
		int r, c, cnt;
		while(size-->0) {
			basket = cloud.poll();
			// 1. 모든 구름이 di 방향으로 si칸 이동한다.
			r = getRC(basket.r + (dr[d] * s));
			c = getRC(basket.c + (dc[d] * s));
			// 2. 물의 양이 1 증가한다.
			map[r][c]++; 
			// 3. 구름이 모두 사라진다.
			remove[r][c] = true; 
			cloud.offer(new Node(r, c));
		}//while
        
		// 4. 물복사버그 마법을 시전한다.
		while(!cloud.isEmpty()) {
			basket = cloud.poll();
			r = basket.r;
			c = basket.c;			
			cnt = copyMagic(r, c); 
			map[r][c] += cnt;
			sum += cnt+1;									
		}//while		
	}//move

	// 물복사버그 마법
	private static int copyMagic(int r, int c) {
		int cnt = 0;
		int nr, nc;
		for(int i=0; i<4; i++) {
			nr = r + ddr[i];
			nc = c + ddc[i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] < 1) continue;
			cnt++;
		}
		return cnt;
	}//copyMagic

	private static int getRC(int n) {
		if(n>=N) return n %= N;
		else if(n<0) {
			n = N + (n%N);
			if(n==N) n = 0;
		}
		
		return n;
	}//getRC
	
	static class Node {
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}	
	}//Node

}//class