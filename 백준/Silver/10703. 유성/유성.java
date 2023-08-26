import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 링크 : https://www.acmicpc.net/problem/10703
 * */
public class Main {
	static int R, C;
	static char[][] map;
	static char[][] result;
	static int[] earthHeight;
	static int[] meteorHeight;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		result = new char[R][C];
		earthHeight = new int[C];
		meteorHeight = new int[C];
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			Arrays.fill(result[i], '.');
			for(int j=0; j<C; j++) {
				if(map[i][j] == '#') {
					if(earthHeight[j] == 0)earthHeight[j] = i;
					result[i][j] = '#';
				}else if(map[i][j] == 'X') {
					meteorHeight[j] = i+1;
				}
			}//for j
		}//for i
		br.close();
		
		down();
		print();
	}//main

	private static void print() {
		StringBuilder ans = new StringBuilder();
		for(int r=0; r<R; r++) {			
			for(int c=0; c<C; c++) {				
				ans.append(result[r][c]);
			}//for c
			ans.append("\n");
		}//for r
		System.out.print(ans);
	}//print

	private static void down() {
		int min = R;
		for(int c=0; c<C; c++) {
			if(meteorHeight[c] == 0) continue;
			min = Math.min(min, earthHeight[c] - meteorHeight[c]);
		}//for
		
		int max, nr;
		for(int c=0; c<C; c++) {
			if(meteorHeight[c] == 0) continue;
			max = meteorHeight[c] + min;
			nr = meteorHeight[c] - 1;
			for(int r=max-1; r>=max-meteorHeight[c]; r--) {
				result[r][c] = map[nr--][c];
			}//for r
		}//for c
		
	}//down

}//class