import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {		
	static int R, C, T;
	static int[][] map, tmpMap; // 방
	static int[] airCleaner = new int[2];

	// 첫째 줄에 T초가 지난 후 구사과 방에 남아있는 미세먼지의 양을 출력한다.
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 첫째 줄에 R, C, T (6 ≤ R, C ≤ 50, 1 ≤ T ≤ 1,000) 가 주어진다.
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C]; // 방
		for(int i=0, a=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				int value = Integer.parseInt(st.nextToken());
				map[i][j] = value;
				if(value == -1) airCleaner[a++] = i; // 공기청정기
			}
		}


		while(T-->0) {
            tmpMap = new int[R][C];
            
			// 1. 미세먼지가 확산된다.						
			first(); 
			fillMap(tmpMap, map);
            
			// 2. 공기청정기가 작동한다.
			second();
			fillMap(map, tmpMap);			
		}
		
		int cnt = getDustCount();
		System.out.println(cnt);

	}//main

    // 미세먼지 양 구하기
	private static int getDustCount() {
		int sum = 0;
		for(int[] tmp : map) {
			for(int dust : tmp) {
				if(dust > 0) sum += dust;
			}
		}
		return sum;
	}//getDustCount

    // 공기청정기 작동
	private static void second() {
		int x1 = airCleaner[0];
		int x2 = airCleaner[1];
		// 공기청정기 위
		for(int i=0; i<C-1; i++) { map[0][i] = tmpMap[0][i+1]; } // 상
		for(int i=1; i<x1; i++) { map[i][0] = tmpMap[i-1][0]; } // 좌
		for(int i=2; i<C; i++) { map[x1][i] = tmpMap[x1][i-1]; } // 하
		for(int i=0; i<x1; i++) { map[i][C-1] = tmpMap[i+1][C-1]; } // 우
		map[x1][1] = 0;
        
        // 공기청정기 아래
		for(int i=2; i<C; i++) { map[x2][i] = tmpMap[x2][i-1]; } // 상
		for(int i=R-2; i>x2; i--) { map[i][0] = tmpMap[i+1][0]; } // 좌
		for(int i=0; i<C-1; i++) { map[R-1][i] = tmpMap[R-1][i+1]; } // 하
		for(int i=x2+1; i<R; i++) { map[i][C-1] = tmpMap[i-1][C-1]; } // 우
		map[x2][1] = 0;
		
	}//second

    // 배열 복사
	private static void fillMap(int[][] from, int[][] to) {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				to[i][j] = from[i][j];
			}
		}
		to[airCleaner[0]][0] = -1;
		to[airCleaner[1]][0] = -1;
	}//fillMap

	private static void first() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] > 0)  dustSpread(i, j, map[i][j]); // 미세먼지 확산
			}
		}
		
	}//first

    // 미세먼지 확산
	private static void dustSpread(int x, int y, int value) {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int cnt = 0;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			int nValue = value/5;
			if(nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == -1) continue;

			tmpMap[nx][ny] += nValue;
			cnt++;
		}
		
		tmpMap[x][y] += value - (value/5) * cnt; 
		
	}//dustSpread
    
}//class