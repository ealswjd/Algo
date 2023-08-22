import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 벽 부수고 이동하기 4
public class Main {
	static int N, M, areaNum;
	static boolean[][] visited;
	static boolean[][] isWall;
	static ArrayList<Integer> cnts;
	static int[][] area;
	static Queue<int[]> q;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N][M];
		isWall = new boolean[N][M];
		area = new int[N][M];
		cnts = new ArrayList<>();
		
		q = new LinkedList<>();
		for(int i=0; i<N; i++) {
			char[] row = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(row[j] == '0') q.offer(new int[] {i, j});										
				else isWall[i][j] = true;				
			}//for j
		}//for i
		br.close();

		breakWall(); 
		printAnswer();		
	}//main

	private static void breakWall() {
		int[] cur;
		int r, c;
		areaNum = 0;
		int cnt = 0;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			if(visited[r][c]) continue;
			areaNum++;
			cnt = getCnt(r, c, 0);
			if(cnt == 0) cnt = 1;
			cnts.add(cnt);
		}//while

	}//breakWall

	private static int getCnt(int r, int c, int cnt) {
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc) || visited[nr][nc] || isWall[nr][nc]) continue;
			visited[nr][nc] = true;
			cnt = getCnt(nr, nc, cnt+1);
		}//for
		
		area[r][c] = areaNum;
		return cnt;
	}//getCnt

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}//rangeCheck

	private static void printAnswer() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!isWall[i][j]) sb.append(0);
				else sb.append(getSum(i, j));
			}//for j
			sb.append("\n");
		}//for i
		
		System.out.print(sb);		
	}//printAnswer

	private static int getSum(int r, int c) {
		int sum = 1;
		boolean[] checked = new boolean[areaNum+1];
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(rangeCheck(nr, nc) || isWall[nr][nc] || checked[area[nr][nc]]) continue;
			checked[area[nr][nc]] = true;
			sum += cnts.get(area[nr][nc]-1);
		}//for
		
		return sum % 10;
	}//getSum

}//class
