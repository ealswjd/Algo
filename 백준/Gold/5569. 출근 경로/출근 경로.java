import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 링크 : https://www.acmicpc.net/problem/5569
 * */
public class Main {
	static int H, W;
	static final int mod = 100000;
	static int[][][][] cnt;
	static int[] dr = {0, 1}; // 동 남
	static int[] dc = {1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		br.close();
		
		cnt = new int[H][W][2][2];
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				Arrays.fill(cnt[i][j][0], -1);
				Arrays.fill(cnt[i][j][1], -1);
			}//for j
		}//for i
		
		cnt[0][0][0][0] = getCnt(0, 1, 0, 0) + getCnt(1, 0, 1, 0);
		System.out.print(cnt[0][0][0][0] % mod);
	}//main

	private static int getCnt(int r, int c, int dir, int check) {
		if(r==H-1 && c==W-1) return 1; // 회사 도착
		if(rangeCheck(r, c)) return 0; // 범위 벗어남
		
		// 경로 갱신되어있으면 반환
		if(cnt[r][c][dir][check] != -1) return cnt[r][c][dir][check];
		
		cnt[r][c][dir][check] = 0;
		
		// 방향 변경 가능
		if(check==0) {
			int nd = dir ^ 1;
			cnt[r][c][dir][check] += getCnt(r+dr[nd], c+dc[nd], nd, 1) % mod;
		}		
		cnt[r][c][dir][check] += getCnt(r+dr[dir], c+dc[dir], dir, 0) % mod;
		
		return cnt[r][c][dir][check];
	}//getCnt

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=H || c<0 || c>=W;
	}//rangeCheck

}//class