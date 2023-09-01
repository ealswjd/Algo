import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 링크 : https://www.acmicpc.net/problem/15683
 * */
public class Main {
	static int N, M; // 사무실의 세로 크기 N과 가로 크기 M
	static int[][] map; // 사무실 각 칸의 정보
	static int[][] cctv;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static final int UP=0, DOWN=1, LEFT=2, RIGHT=3;
	static int max, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctv = new int[8][3];
		
		C = 0;
		int blindSpot = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) blindSpot++;
				else if(map[i][j] < 6) {
					cctv[C][0] = map[i][j];
					cctv[C][1] = i;
					cctv[C++][2] = j;
				}//else
			}//for j
		}//for i
		br.close();
		
		max = 0;
		surveillance(0, 0, new boolean[N][M]);
		System.out.print(blindSpot-max);
	}//main

	private static void surveillance(int idx, int sum, boolean[][] checked) {
		if(idx == C) {
			max = Math.max(max, sum);
			return;
		}//if
		
		int num = cctv[idx][0];
		int r = cctv[idx][1];
		int c = cctv[idx][2];
		
		boolean[][] tmpChecked = copyChecked(checked);
		
		int cnt = 0, up, down, left, right;
		switch (num) {
		case 1: // 한 방향
			up = getCnt(r, c, UP, 0, tmpChecked);
			surveillance(idx+1, sum+up, tmpChecked);
			
			tmpChecked = copyChecked(checked);			
			down = getCnt(r, c, DOWN, 0, tmpChecked);
			surveillance(idx+1, sum+down, tmpChecked);
			
			tmpChecked = copyChecked(checked);			
			left = getCnt(r, c, LEFT, 0, tmpChecked);
			surveillance(idx+1, sum+left, tmpChecked);
			
			tmpChecked = copyChecked(checked);			
			right = getCnt(r, c, RIGHT, 0, tmpChecked);
			surveillance(idx+1, sum+right, tmpChecked);
			break;
		case 2: // 반대 방향
			up = getCnt(r, c, UP, 0, tmpChecked);
			down = getCnt(r, c, DOWN, 0, tmpChecked);
			cnt = up+down;
			surveillance(idx+1, sum+cnt, tmpChecked);
			
			tmpChecked = copyChecked(checked);						
			left = getCnt(r, c, LEFT, 0, tmpChecked);
			right = getCnt(r, c, RIGHT, 0, tmpChecked);
			cnt = left + right;
			surveillance(idx+1, sum+cnt, tmpChecked);
			
			break;
		case 3: // 직각			
			up = getCnt(r, c, UP, 0, tmpChecked);
			left = getCnt(r, c, LEFT, 0, tmpChecked);
			cnt = up + left;
			surveillance(idx+1, sum+cnt, tmpChecked);
						
			tmpChecked = copyChecked(checked);			
			up = getCnt(r, c, UP, 0, tmpChecked);
			right = getCnt(r, c, RIGHT, 0, tmpChecked);
			cnt = up + right;
			surveillance(idx+1, sum+cnt, tmpChecked);			
			
			tmpChecked = copyChecked(checked);			
			down = getCnt(r, c, DOWN, 0, tmpChecked);
			right= getCnt(r, c, RIGHT, 0, tmpChecked);
			cnt = down + right;
			surveillance(idx+1, sum+cnt, tmpChecked);			
			
			tmpChecked = copyChecked(checked);			
			down = getCnt(r, c, DOWN, 0, tmpChecked);
			left= getCnt(r, c, LEFT, 0, tmpChecked);
			cnt = down + left;
			surveillance(idx+1, sum+cnt, tmpChecked);
			
			break;
		case 4: // 세 방향			
			up = getCnt(r, c, UP, 0, tmpChecked);
			left= getCnt(r, c, LEFT, 0, tmpChecked);
			right= getCnt(r, c, RIGHT, 0, tmpChecked);
			cnt = up + left + right;
			surveillance(idx+1, sum+cnt, tmpChecked);
			
			tmpChecked = copyChecked(checked);						
			down = getCnt(r, c, DOWN, 0, tmpChecked);
			left= getCnt(r, c, LEFT, 0, tmpChecked);
			right= getCnt(r, c, RIGHT, 0, tmpChecked);
			cnt = down + left + right;
			surveillance(idx+1, sum+cnt, tmpChecked);
			
			tmpChecked = copyChecked(checked);						
			up = getCnt(r, c, UP, 0, tmpChecked);
			down = getCnt(r, c, DOWN, 0, tmpChecked);
			left= getCnt(r, c, LEFT, 0, tmpChecked);
			cnt = up + down + left;
			surveillance(idx+1, sum+cnt, tmpChecked);
			
			tmpChecked = copyChecked(checked);						
			up = getCnt(r, c, UP, 0, tmpChecked);
			down = getCnt(r, c, DOWN, 0, tmpChecked);
			right= getCnt(r, c, RIGHT, 0, tmpChecked);
			cnt = up + down + right;
			surveillance(idx+1, sum+cnt, tmpChecked);
			
			break;
		case 5: // 네 방향			
			up = getCnt(r, c, UP, 0, tmpChecked);
			down = getCnt(r, c, DOWN, 0, tmpChecked);
			left= getCnt(r, c, LEFT, 0, tmpChecked);
			right= getCnt(r, c, RIGHT, 0, tmpChecked);
			cnt = up + down + left + right;
			surveillance(idx+1, sum+cnt, tmpChecked);
			
			break;
		}//switch		
		
	}//getCnt

	private static boolean[][] copyChecked(boolean[][] checked) {
		boolean[][] tmpChecked = new boolean[N][M];
		for(int i=0; i<N; i++) {
			tmpChecked[i] = Arrays.copyOf(checked[i], M);
		}
		return tmpChecked;
	}//copyChecked

	private static int getCnt(int r, int c, int dir, int cnt, boolean[][] checked) {
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		
		if(rangeCheck(nr, nc) || map[nr][nc] == 6) return cnt;
		
		if(checked[nr][nc] || map[nr][nc] > 0) cnt = getCnt(nr, nc, dir, cnt, checked);
		else {
			checked[nr][nc] = true;
			cnt = getCnt(nr, nc, dir, cnt+1, checked);
		}//else
		
		return cnt;
	}//getCnt

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >=M;
	}//rangeCheck

}//class