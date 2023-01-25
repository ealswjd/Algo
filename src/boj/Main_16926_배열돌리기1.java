package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16926
public class Main_16926_배열돌리기1 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 배열의 크기 N, M
		int M = Integer.parseInt(st.nextToken()); // 배열의 크기 N, M
		int R = Integer.parseInt(st.nextToken()); // 수행해야 하는 회전의 수
		int[][] arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<R; i++) {
			rotation(arr);
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();

	}//main
	
	private static void rotation(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int groupCnt = Math.min(N, M) / 2;
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		int x=0, y=0, idx=0;
		
		for(int i=0; i<groupCnt; i++) {
			x = i;
			y = i;
			idx = 0;
			int tmp = arr[x][y];
			while(idx < 4) {
				int nx = x + dx[idx];
				int ny = y + dy[idx];
				if(nx>=i && nx<N-i && ny>=i && ny<M-i) {
					arr[x][y] = arr[nx][ny];
					x = nx;
					y = ny;
				}else idx++;
			}
			arr[i+1][i] = tmp;
		}//for
		
		
	}//rotation

}//class
