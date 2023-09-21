import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int[][] map;
	static int[][] rowArray, colArray, squareArray;	
	static ArrayList<int[]> list;
	static StringBuilder sb = new StringBuilder();
	static boolean flag = true;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		
		map = new int[9][9]; // 미완성 스도쿠
		rowArray = new int[9][10]; // 행 체크
		colArray = new int[9][10]; // 열 체크
		squareArray = new int[9][10]; // 3×3 크기 사각형 체크
		for(int i=0; i<9; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<9; j++) {
				map[i][j] = tmp[j] - '0';
				if(map[i][j] != 0) { // 채워진 칸이라면
					rowArray[i][map[i][j]] = 1;
					colArray[j][map[i][j]] = 1;
					squareArray[(i/3)*3+j/3][map[i][j]] = 1;
				}else {
					list.add(new int[] {i, j});
				}
			}
		}//for
		
		sudoku(0);
		System.out.print(sb);

	}//main

	private static void sudoku(int start) {
		if(!flag) return;
		if(start == list.size()) { // 스도쿠 완성
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			flag = false;
			return;
		}
		
		int[] tmp = list.get(start);
		int r = tmp[0]; // 행
		int c = tmp[1]; // 열
		for(int i=1; i<10; i++) {
			if(rowArray[r][i] == 0 && colArray[c][i] == 0 && squareArray[(r/3)*3+c/3][i] == 0 && flag) {
				rowArray[r][i] = colArray[c][i] = squareArray[(r/3)*3+c/3][i] = 1; // 체크
				map[r][c] = i; // 값 채워주기
				sudoku(start+1);
				rowArray[r][i] = colArray[c][i] = squareArray[(r/3)*3+c/3][i] = 0; // 체크 해제
			}
		}//for
		
	}//sudoku

}//class