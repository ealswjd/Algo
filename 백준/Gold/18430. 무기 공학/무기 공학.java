import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18430
public class Main {
	static int R, C, max;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C];
		
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}//for c
		}//for r
		
		if(R >= 2 && C >= 2) getMax(0, 0);

		System.out.print(max);
	}//main

	private static void getMax(int cnt, int sum) {
		if(cnt == R*C) {
			max = Math.max(max, sum);
			return;
		}//if
		
		int r = cnt / C;
		int c = cnt % C;
		int tmp = sum + 2 * map[r][c];
		
		if(!visited[r][c]) {
			
			/* o o
			  	 o	*/			 
			if(r+1 < R && c-1 >= 0 && !visited[r+1][c] && !visited[r][c-1]) {
				visited[r][c] = true;
				visited[r+1][c] = true;
				visited[r][c-1] = true;
				getMax(cnt+1, tmp + map[r+1][c] + map[r][c-1]);
				visited[r][c] = false;
				visited[r+1][c] = false;
				visited[r][c-1] = false;
			}//if
			
			/*   o
			   o o	*/			 
			if(r-1 >= 0 && c-1 >= 0 && !visited[r-1][c] && !visited[r][c-1]) {
				visited[r][c] = true;
				visited[r-1][c] = true;
				visited[r][c-1] = true;
				getMax(cnt+1, tmp + map[r-1][c] + map[r][c-1]);
				visited[r][c] = false;
				visited[r-1][c] = false;
				visited[r][c-1] = false;
			}//if
			
			/* o
			   o o	*/	
			if(r-1 >= 0 && c+1 < C && !visited[r-1][c] && !visited[r][c+1]) {
				visited[r][c] = true;
				visited[r-1][c] = true;
				visited[r][c+1] = true;
				getMax(cnt+1, tmp + map[r-1][c] + map[r][c+1]);
				visited[r][c] = false;
				visited[r-1][c] = false;
				visited[r][c+1] = false;
			}//if
			
			/* o o
			   o  	*/	
			if(r+1 < R && c+1 < C && !visited[r+1][c] && !visited[r][c+1]) {
				visited[r][c] = true;
				visited[r+1][c] = true;
				visited[r][c+1] = true;
				getMax(cnt+1, tmp + map[r+1][c] + map[r][c+1]);
				visited[r][c] = false;
				visited[r+1][c] = false;
				visited[r][c+1] = false;
			}//if
			
		}//if
		
		getMax(cnt+1, sum);		
	}//getMax

}//class