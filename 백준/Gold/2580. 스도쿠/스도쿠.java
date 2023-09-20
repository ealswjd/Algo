import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 링크 : https://www.acmicpc.net/problem/2580
 * */
public class Main {
	static final int R=9, C=9;
	static int[][] map; 
	static boolean[][] row, col, square;
	static ArrayList<int[]> emptyList;
	static StringBuilder ans;
	static boolean complete;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init();
		
		StringTokenizer st;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) emptyList.add(new int[] {i, j});
				else {
					row[i][map[i][j]] = true;
					col[j][map[i][j]] = true;
					square[(i/3)*3 + j/3][map[i][j]] = true;
				}
			}//for j
		}//for i
		br.close();		
		
		cnt = emptyList.size();		
		fill(0);

		System.out.print(ans);
	}//main

	private static void fill(int start) {
		if(complete) return;
		if(start == cnt) {
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					ans.append(map[i][j]).append(" ");
				}//for j
				ans.append("\n");
			}//for i
			complete = true;
			return;
		}//if
		
		int[] cur = emptyList.get(start);
		int r = cur[0];
		int c = cur[1];
		for(int n=1; n<=R; n++) {
			if(row[r][n] || col[c][n] || square[(r/3)*3+c/3][n] || complete) continue;
			row[r][n] = col[c][n] = square[(r/3)*3+c/3][n] = true;
			map[r][c] = n;
			fill(start+1);
			row[r][n] = col[c][n] = square[(r/3)*3+c/3][n] = false;
		}//for
		
	}//fill

	private static void init() {
		map = new int[R][C]; // 스도쿠
		row = new boolean[R][C+1]; // 행 체크
		col = new boolean[R][C+1]; // 열 체크
		square = new boolean[R][C+1]; // 사각형 체크
		emptyList = new ArrayList<>(); // 빈칸 
		complete = false; // 스도쿠 완성
		ans = new StringBuilder(); 
	}//init

}//class