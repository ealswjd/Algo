import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2186
public class Main {
	static int N, M, K, len;
	static char[][] map; // 문자판
	static char[] word; // 만들어야 되는 단어
	static int[][][] dp;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		K = Integer.parseInt(st.nextToken()); // 이동 가능한 칸 개수

		map = new char[N][M]; // 문자판
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}//for i
		
		word = br.readLine().toCharArray(); // 만들어야 되는 단어
		br.close();

		len = word.length;
		
		dp = new int[N][M][len];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				Arrays.fill(dp[i][j], -1);				
			}//for j
		}//for i
		
		int result = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c] == word[0]) result += getCnt(r, c, 0);				
			}//for c
		}//for r
		
		System.out.print(result);
	}//main

	private static int getCnt(int r, int c, int cnt) {
		if(cnt == len-1) return 1;		
		if(dp[r][c][cnt] != -1) return dp[r][c][cnt];
		
		dp[r][c][cnt] = 0;
		for(int i=0; i<4; i++) {
			for(int j=1; j<=K; j++) {
				int nr = r + dr[i] * j;
				int nc = c + dc[i] * j;
				if(rangeCheck(nr, nc) || map[nr][nc] != word[cnt+1]) continue;
				dp[r][c][cnt] += getCnt(nr, nc, cnt+1);
			}//for j
		}//for
		
		return dp[r][c][cnt];
	}//getCnt

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}//rangeCheck

}//class