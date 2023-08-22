import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 벽 부수고 이동하기 4
public class Main {
	static int N, M, areaNum;
	static int[][] visited;
	static char[][] map;
	static int[] cnts;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new int[N][M];
		map = new char[N][M];
		cnts = new int[100000];

		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();			
		}//for i
		br.close();

		areaNum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == '1' || visited[i][j] > 0) continue;
				visited[i][j] = ++areaNum;
				getArea(i, j);
			}//for j
		}//for i
		
		printAnswer();		
	}//main


	private static void getArea(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		int cnt = 1;
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(rangeCheck(nr, nc) || visited[nr][nc] > 0 || map[nr][nc] == '1') continue;
				cnt++;
				visited[nr][nc] = areaNum;
				q.offer(new int[] {nr, nc});
			}//for
		}//while
		
		cnts[areaNum] = cnt;
	}//getArea


	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}//rangeCheck

	private static void printAnswer() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == '0') sb.append(0);
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
			if(rangeCheck(nr, nc) || map[nr][nc] == '1' || checked[visited[nr][nc]]) continue;
			checked[visited[nr][nc]] = true;
			sum += cnts[visited[nr][nc]];
		}//for
		
		return sum % 10;
	}//getSum

}//class