import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17136
public class Main {
	static final int R=10, C=10; // 종이 크기
	static int min;
	static int[][] map; // 크기가 10×10인 종이
	static int[] size = {5, 4, 3, 2, 1};
	static int[] paperCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		br.close();
		
		min = 987654321;
		paperCnt = new int[] {5, 5, 5, 5, 5};
		getCnt(0, 0, 0);
		
		if(min == 987654321) min = -1;
		System.out.print(min);
	}//main

	private static void getCnt(int r, int c, int sum) {
		if(r>=R-1 && c >= C) {
			min = Math.min(min, sum);
			return;
		}//if
		if(sum >= min) return;
		if(c >= C) {
			getCnt(r+1, 0, sum);
			return;
		}//if
		
		if(map[r][c] != 1) getCnt(r, c+1, sum);
		else {
			for(int i=0; i<5; i++) {
				if(paperCnt[i] > 0 && possible(r, c, i)) {
					paperCnt[i]--;
					changeStatus(r, c, 0, i);
					getCnt(r, c+1, sum+1);
					paperCnt[i]++;
					changeStatus(r, c, 1, i);					
				}//if
			}//for
		}//else
		
	}//getCnt

	
	private static void changeStatus(int r, int c, int status, int idx) {
		int endR = r + size[idx];
		int endC = c + size[idx];
		for(int i=r; i<endR; i++) {
			for(int j=c; j<endC; j++) {
				map[i][j] = status;
			}//c
		}//r		
	}//changeStatus

	private static boolean possible(int r, int c, int idx) {
		int endR = r + size[idx];
		int endC = c + size[idx];
		for(int i=r; i<endR; i++) {
			for(int j=c; j<endC; j++) {
				if(rangeCheck(i, j) || map[i][j] != 1) return false;
			}//c
		}//r
		
		return true;
	}//possible

	// 범위 체크
	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}//rangeCheck

}//class