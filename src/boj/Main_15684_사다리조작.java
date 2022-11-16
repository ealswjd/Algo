package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15684_사다리조작 {
	static int N, M, H;
	static int[][] map;
	static boolean finish;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로선
		M = Integer.parseInt(st.nextToken()); // 가로선
		H = Integer.parseInt(st.nextToken()); // 가로선을 놓을 수 있는 위치의 개수
		
		map = new int[H+1][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
		}
		
		int ans = 0;
		for(int i=0; i<4; i++) {
			dfs(i, 1, 0);
			if(finish) {
				ans = i;
				break;
			}
		}
		
		ans = finish ? ans : -1;
		System.out.print(ans);
		br.close();
	}//main

	private static void dfs(int target, int start, int cnt) {
		if(finish) return;
		if(cnt == target) {
			if(check()) finish = true;
			return;
		}
		
		for(int i=start; i<=H; i++) {
			for(int j=1; j<N; j++) {
				if(map[i][j] == 1) continue;
				map[i][j] = 1;
				dfs(target, i, cnt+1);
				map[i][j] = 0;
			}
		}//for
		
	}//dfs

	private static boolean check() {
		int c = 0;
		for(int i=1; i<=N; i++) {
			c = i;
			for(int j=1; j<=H; j++) {
				if(map[j][c] == 1) c++;
				else if(map[j][c-1] == 1) c--;				
			}//for j
			if(c != i) return false;
		}//for i
		return true;
	}//check


}//class
