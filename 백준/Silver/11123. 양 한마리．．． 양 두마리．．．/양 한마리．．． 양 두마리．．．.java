import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11123
public class Main {
	static int H, W;
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			
			for(int i=0; i<H; i++) {
				map[i] = br.readLine().toCharArray();
			}//for i
			
			int cnt = 0;
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					if(map[i][j] == '#') {
						dfs(i, j);
						cnt++;
					}//if
				}//for j
			}//for i	
			
			ans.append(cnt).append('\n');
		}//while
		
		br.close();
		System.out.print(ans);
	}//main

	private static void dfs(int r, int c) {
		map[r][c] = '@';
		int nr, nc;
		for(int i=0; i<4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if(rangeCheck(nr, nc)) continue;
			dfs(nr, nc);
		}//for
		
	}//dfs

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=H || c<0 || c>=W || map[r][c] != '#';
	}//rangeCheck

}//class