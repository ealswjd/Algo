import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19236
public class Main {
	static int maxSum; // 물고기 번호의 합
	static int max;
	// 				상, 좌상, 좌, 좌하, 하, 우하, 우, 우상
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[4][4];   // 4×4크기의 공간
		Fish[] fish = new Fish[17]; // 물고기
		
		StringTokenizer st;
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken()) - 1;
				map[i][j] = a;
				fish[a] = new Fish(a, b, i, j, false);
			}//for j
		}//for i
		br.close();		
		
		max = map[0][0];		
		fish[max].eaten = true;
		maxSum = max;

		int dir = fish[map[0][0]].dir;
		int r = 0, c = 0;
		eat(map, fish, r, c, dir, max); // 물고기 번호의 합								
		
		System.out.print(maxSum);
	}//main

	private static void eat(int[][] map, Fish[] fish, int r, int c, int dir, int sum) {
		maxSum = Math.max(maxSum, sum);
		
		// 초기화
		int[][] tmp = new int[4][4];
		Fish[] fishTmp = new Fish[17];
		for(int i=0; i<4; i++) tmp[i] = Arrays.copyOf(map[i], map.length);
		for(int i=1; i<17; i++) {
			fishTmp[i] = new Fish(fish[i].num, fish[i].dir, fish[i].r, fish[i].c, fish[i].eaten);
		}		
		
		// 물고기 이동
		fishMove(r, c, tmp, fishTmp);
		
		int nr=r, nc=c;		
		while(!rangeCheck(nr, nc)) {
			nr += dr[dir];
			nc += dc[dir];
			
			if(rangeCheck(nr, nc)) break;
			
			if(fishTmp[tmp[nr][nc]].eaten) continue;
			fishTmp[tmp[nr][nc]].eaten = true;
			eat(tmp, fishTmp, nr, nc, fishTmp[tmp[nr][nc]].dir, sum+tmp[nr][nc]);		
			fishTmp[tmp[nr][nc]].eaten = false;
		}//while
		
	}//eat


	private static void fishMove(int sr, int sc, int[][] tmp, Fish[] fishTmp) {
		int r, c, nr, nc, d, cnt;
		
		for(int i=1, size=17; i<size; i++) {
			if(fishTmp[i].eaten) continue;
			
			r = fishTmp[i].r;
			c = fishTmp[i].c;
			d = fishTmp[i].dir;
			nr = r + dr[d];
			nc = c + dc[d];
			cnt = 0;
            
			while((rangeCheck(nr, nc) || (sr == nr && sc == nc)) && cnt < 8) {
				d = (d+1)%8;
				nr = r + dr[d];
				nc = c + dc[d];
				cnt++;
			}//while
			if(rangeCheck(nr, nc) || (sr == nr && sc == nc)) continue;
            
			fishTmp[i].dir = d;
			tmp[r][c] = tmp[nr][nc];	
			fishTmp[tmp[r][c]].r = r;
			fishTmp[tmp[r][c]].c = c;
			tmp[nr][nc] = i;
			fishTmp[i].r = nr;
			fishTmp[i].c = nc;
		}//for
		
	}//fishMove

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= 4 || c < 0 || c >= 4;
	}//rangeCheck

	static class Fish {
		int num;
		int dir;
		int r;
		int c;
		boolean eaten;		
		public Fish(int num, int dir, int r, int c, boolean eaten) {
			this.num = num;
			this.dir = dir;
			this.r = r;
			this.c = c;
			this.eaten = eaten;
		}//constructor
	}//Fish
}//class