package sewa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1767_프로세서연결하기 {

	static int N,tc,map[][],size;
	static ArrayList<Core> list;
	static int answer;
	static boolean[] visited;
	static int[][] d = {{1,0},{0,1},{0,-1},{-1,0}}; // 아, 오, 왼, 위
	public static void main(String[] args) throws Exception {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(sc.readLine());
		tc = Integer.parseInt(st.nextToken());
		for( int idx = 1; idx<= tc; idx++) {
			answer = Integer.MAX_VALUE;
			list = new ArrayList<>();
			st = new StringTokenizer(sc.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for( int i=0;i<N;i++) map[i]=Arrays.stream(sc.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); 
			for( int r = 1; r < N-1; r++) {
				for( int c = 1; c < N-1; c++) {
					if( map[r][c]==1 ) {
						list.add(new Core(r, c));
					}
				}
			}
			size = list.size();
			visited = new boolean[size];
			for(int i=size; i>0;i--) {
				game(i,0,0);
				if(answer != Integer.MAX_VALUE) break;
			}
			if(answer != Integer.MAX_VALUE) {
				System.out.printf("#%d %d\n",idx,answer);
			}
			else {
				System.out.printf("#%d %d\n",idx,0);
			}

		}
	}

	public static void game( int num, int start, int depth ) {
		if( num == depth ) {
			dfs( num, 0 ,0,0);// 이거는 몇 개가 선택되었는지를 알려주는거
			return;
		}
		for( int i =start;i< size; i++) {
			if(visited[i]==false) {
				visited[i]=true;
				game(num, i+1, depth + 1);
				visited[i]=false;
			}
		}
	}

	public static void dfs( int num, int k, int selected, int temp ) {
		if( answer < temp ) return;
		if( k == size && (num != selected) ) return;
		if( num == selected ) {
			answer = Math.min(answer, temp);
			return;
		}
		if( visited[k] == false ) {
			dfs(num, k+1,selected,temp);
			return;
		}
		else {
			Core nowCore = list.get(k);
			for( int i = 0; i < 4; i++ ) {
				int r = nowCore.r;
				int c = nowCore.c;
				boolean success = false;
				int T = 0;
				while(true) {
					r = r + d[i][0];
					c = c + d[i][1];
					if(!isValid(r,c)) {
						success = true;
						dfs(num, k+1, selected+1, temp+T);
						while(true) {
							r = r - d[i][0];
							c = c - d[i][1];
							if( r == nowCore.r && c == nowCore.c ) {
								break;
							}
							map[r][c]= 0;
						}
						break;
					}
					if(isValid(r, c)) {
						if( map[r][c] == 0 ) {
							map[r][c] = 2;
							T++;
							continue;
						} else {
							while(true) {
								r = r - d[i][0];
								c = c - d[i][1];
								if( r == nowCore.r && c == nowCore.c ) {
									break;
								}
								map[r][c]= 0;
							}
							break;
						}
					}
				}
			}
		}
	}

	public static boolean isValid( int ROW, int COL ) {
		return 0<= ROW && 0<= COL && ROW < N && COL < N;
	}

	static class Core{
		int r,c;
		public Core(int r,int c) {
			this.r = r;
			this.c = c;
		}
	}
}
