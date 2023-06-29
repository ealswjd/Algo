import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int minR, maxR, minC, maxC;
	static int R, C;
	static char[][] map;
	static boolean[][] melted;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		melted = new boolean[R][C];
		minR = minC = 100;
		maxR = maxC = -1;
		
		Queue<int[]> q = new LinkedList<>();
		for(int i=0; i<R; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				map[i][j] = tmp[j];
				if(tmp[j] == 'X') q.offer(new int[] {i, j});
			}//for j
		}//for i		
		br.close();
		
		bfs(q); // 50년 후
		printMap(); // 50년 후의 지도를 출력
		
	}//main

	private static void printMap() {
        if(minR == 100) return;
		StringBuilder sb = new StringBuilder();
		
		for(int r=minR; r<=maxR; r++) {
			for(int c=minC; c<=maxC; c++) {
				if(melted[r][c]) sb.append('.');
				else sb.append(map[r][c]);
			}// for c
			sb.append("\n");
		}//for r
		
		System.out.print(sb);		
	}//printMap

	private static void bfs(Queue<int[]> q) {
		int cnt, r, c;
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll(); // 현재 땅
			r = cur[0];
			c = cur[1];
			cnt = 0;
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(check(nr, nc)) cnt++;
			}//for
			
			if(cnt > 2) { // 인접한 세 칸 또는 네 칸에 바다가 있는 땅이라면
				melted[r][c] = true; // 잠김
				continue;
			}
			minR = Math.min(minR, r);
			minC = Math.min(minC, c);
			maxR = Math.max(maxR, r);
			maxC = Math.max(maxC, c);
		}//while
		
	}//bfs

	// 지구온난화 조건 체크
	private static boolean check(int r, int c) {
		return r<0 || r>=R || c<0 || c>=C || map[r][c]=='.';
	}//check

}//class