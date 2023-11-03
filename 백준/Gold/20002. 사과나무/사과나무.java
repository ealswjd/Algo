import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20002
public class Main {
	static int N, max;
	static int[][] cost;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		max = -1000;

		StringTokenizer st;
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());			
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[r][c]);
			}//for c
		}//for r
		br.close();
		
		cost = new int[N+1][N+1];
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				cost[r][c] = map[r-1][c-1] + cost[r-1][c] + cost[r][c-1] - cost[r-1][c-1];
			}//for c
		}//for r
		
		for(int k=2; k<=N; k++) {
			max = Math.max(max, getSum(k));
		}//for
		
		System.out.print(max);
	}//main

	private static int getSum(int k) {
		int result = -1000;
		int sum;
		
		for(int r=k; r<=N; r++) {
			for(int c=k; c<=N; c++) {
				sum = cost[r][c] - cost[r-k][c] - cost[r][c-k] + cost[r-k][c-k];
				result = Math.max(result, sum);
			}//for c
		}//for r
		
		return result;
	}//getSum

}//class