import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20002
public class Main {
	static int N, max;
	static int[][] row;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		row = new int[N+1][N+1];
		max = -1000;
		
		StringTokenizer st;
		int n;
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(br.readLine());			
			for(int c=1; c<=N; c++) {
				n = Integer.parseInt(st.nextToken());
				max = Math.max(max, n);
				row[r][c] = row[r][c-1] + n;
			}//for c
		}//for r
		br.close();
		
		for(int k=3; k<=N; k++) {
			for(int i=k; i<=N; i++) {
				for(int j=k; j<=N; j++) {
					max = Math.max(max, getSum(k, i, j));
				}//for j
			}//for i
			
		}//for k
		
		System.out.print(max);

	}//main

	private static int getSum(int k, int r, int c) {
		int start = r-k+1;
		int sum = 0;
		for(int i=start; i<=r; i++) {
			sum += row[i][c] - row[i][c-k];
		}//for
		return sum;
	}//getSum

}//class