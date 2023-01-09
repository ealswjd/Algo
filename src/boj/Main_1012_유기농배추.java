package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1012_유기농배추 {
	static boolean[][] checked;
	static int[][] cabbage;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int m, n, k; // 가로길이, 세로길이, 배추 위치 개수
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		int t = Integer.parseInt(br.readLine()); // 테스트케이스의 개수
		
		for(int i=0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			checked = new boolean[m][n];
			cabbage = new int[m][n];
			
			System.out.println(getWormCnt(br, st));
		}
		br.close();

	}//main
	
	static int getWormCnt(BufferedReader br, StringTokenizer st) throws IOException{
		int cnt = 0;
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			cabbage[x][y] = 1;
		}
		
		for(int x=0; x<m; x++) {
			for(int y=0; y<n; y++) {
				if(!checked[x][y] && cabbage[x][y]==1) {
					dfs(x, y);
					cnt++;
				}
			}
		}
		return cnt;
	}//getWormCnt
	
	static void dfs(int x, int y) {
		checked[x][y] = true;
		for(int i=0; i<4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
            
			if(nextX < 0 || nextY < 0 || nextX >= m || nextY >= n) continue;
			if(checked[nextX][nextY] || cabbage[nextX][nextY] != 1) continue;
			dfs(nextX, nextY);
		}
	}//dfs
    
}// Main class
