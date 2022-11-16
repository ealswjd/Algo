package sewa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1949_등산로조성 {
	static int N, K, max, top;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			sb.append("#"+tc+" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도의 한 변의 길이 N
			K = Integer.parseInt(st.nextToken()); // 최대 공사 가능 깊이 K
			top = Integer.MIN_VALUE; // 가장 높은 봉우리 
			visited = new boolean[N][N];
			
			map = new int[N][N]; // N * N 크기의 지도
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(top < map[i][j]) top = map[i][j];						
				}
			}//N * N 크기의 지도 정보
			
			max = 1;
			for(int x=0; x<N; x++) {
				for(int y=0; y<N; y++) {
					if(top == map[x][y]) {
						getMax(x, y, map[x][y], 1, false);
					}
				}
			}
			
			sb.append(max).append("\n");
		}//for tc
		
		br.close();
		System.out.print(sb);
	}//main

	private static void getMax(int x, int y, int start, int cnt, boolean isUsed) {
		max = Math.max(max, cnt);		
		visited[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

			if(map[nx][ny] < start) {
				getMax(nx, ny, map[nx][ny], cnt+1, isUsed);
			}else if(!isUsed && map[nx][ny] >= start && map[nx][ny]-start < K ) {
				getMax(nx, ny, start-1, cnt+1, true);
			}
		}//사방탐색
		visited[x][y] = false;
		
	}//getMax

}//class

/*

10
5 1
9 3 2 3 2
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8
3 2
1 2 1
2 1 2
1 2 1
5 2
9 3 2 3 2
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8
4 4
8 3 9 5
4 6 8 5
8 1 5 1
4 9 5 5
4 1
6 6 1 7
3 6 6 1
2 4 2 4
7 1 3 4
5 5
18 18 1 8 18
17 7 2 7 2
17 8 7 4 3
17 9 6 5 16
18 10 17 13 18
6 4
12 3 12 10 2 2
13 7 13 3 11 6
2 2 6 5 13 9
1 12 5 4 10 5
11 10 12 8 2 6
13 13 7 4 11 7
7 3
16 10 14 14 15 14 14
15 7 12 2 6 4 9
10 4 11 4 6 1 1
16 4 1 1 13 9 14
3 12 16 14 8 13 9
3 4 17 15 12 15 1
6 6 13 6 6 17 12
8 5
2 3 4 5 4 3 2 1
3 4 5 6 5 4 3 2
4 5 6 7 6 5 4 3
5 6 7 8 7 6 5 4
6 7 8 9 8 7 6 5
5 6 7 8 7 6 5 4
4 5 6 7 6 5 4 3
3 4 5 6 5 4 3 2
8 2
5 20 15 11 1 17 10 14
1 1 11 16 1 14 7 5
17 2 3 4 5 13 19 20
6 18 5 16 6 7 8 5
10 4 5 4 9 2 10 16
2 7 16 5 8 9 10 11
12 19 18 8 7 11 15 12
1 20 18 17 16 15 14 13


------>

#1 6
#2 3
#3 7
#4 4
#5 2
#6 12
#7 6
#8 7
#9 10
#10 19

*/