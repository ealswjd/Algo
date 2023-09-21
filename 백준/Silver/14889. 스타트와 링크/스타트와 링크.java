import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14889
public class Main {
	static int N, min;
	static int[][] map;
	static boolean[] startTeam;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		br.close();
		
		startTeam = new boolean[N];
		min = 987654321;
		splitTeam(0, 0);
		System.out.print(min);
	}//main

	private static void splitTeam(int num, int cnt) {
		if(cnt == N/2) {
			getMin();
			return;
		}//if
		
		for(int i=num; i<N; i++) {
			if(startTeam[i]) continue;
			startTeam[i] = true;
			splitTeam(i+1, cnt+1);
			startTeam[i] = false;
		}//for
		
	}//splitTeam

	private static void getMin() {
		int start = 0, link = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(startTeam[i] && startTeam[j]) {
					start += map[i][j];
				}else if(!startTeam[i] && !startTeam[j]) {
					link += map[i][j];
				}
			}//for j
		}//for i
		
		min = Math.min(min, Math.abs(start - link));
		
	}//getMin

}//class