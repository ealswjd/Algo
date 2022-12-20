package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18428
public class Main_18428_감시피하기 {
	static int N, cnt;
	static char[][] map;
	static ArrayList<int[]> tList, xList;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0, 0};
	static int[] dc = {0, 0, -1, 1, 0};
	static boolean flag=false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];
		
		StringTokenizer st;
		tList = new ArrayList<>();
		xList = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == 'T') tList.add(new int[] {i, j});
				else if(map[i][j] == 'X') xList.add(new int[] {i, j});
			}//for j
		}//for i
		br.close();		
		
		comb(0, 0);

		if(flag) System.out.println("YES");
		else System.out.println("NO");
		
	}//main

	private static void comb(int start, int cnt) {
		if(flag) return;
		if(cnt==3) {
			boolean b = true;
			for(int i=0, size=tList.size(); i<size; i++) {
				int[] tmp = tList.get(i);
				if(!check(tmp[0], tmp[1], true, 0, 4)) {
					b = false;
					break;
				}
			}//for
			flag = b;
			return;
		}
		
		for(int i=start; i<xList.size(); i++) {
			int r = xList.get(i)[0]; 
			int c = xList.get(i)[1];
			if(visited[r][c]) continue;
			visited[r][c] = true;
			map[r][c] = 'O';
			comb(i+1, cnt+1);
			map[r][c] = 'X';
			visited[r][c] = false;
		}
		
	}//comb

	private static boolean check(int r, int c, boolean flag, int d, int max) {
		if(!flag) return false;
		for(int i=d; i<max; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr<0 || nr>=N || nc<0|| nc>=N ) continue;
			if(map[nr][nc]=='T' || map[nr][nc]=='O') continue;
			if(map[nr][nc]=='S') {
				return false;
			}
			flag = check(nr, nc, flag, i, i+1);
		}//for
		
		return flag;
	}//check

	

}//class

/*

5
X S X X T
T X S X X
X X X X X
X T X X X
X X T X X

*/