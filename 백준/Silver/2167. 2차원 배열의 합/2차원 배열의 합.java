import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2167
public class Main {
	static int N, M;
	static int[][] map, sum;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		sum = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + map[i][j];
				map[i][j] = sum[i][j];
			}//for j
		}//for i
		
		StringBuilder ans = new StringBuilder();
		int K = Integer.parseInt(br.readLine());
		int r1, c1, r2, c2;
		while(K-->0) {
			st = new StringTokenizer(br.readLine());
			r1 = Integer.parseInt(st.nextToken());
			c1 = Integer.parseInt(st.nextToken());
			r2 = Integer.parseInt(st.nextToken());
			c2 = Integer.parseInt(st.nextToken());
			
			ans.append(sum[r2][c2] - sum[r2][c1-1] - sum[r1-1][c2] + sum[r1-1][c1-1])
				.append('\n');
		}//while

		System.out.print(ans);
	}//main

}//class