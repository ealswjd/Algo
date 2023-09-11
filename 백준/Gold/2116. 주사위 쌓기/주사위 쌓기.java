import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2116
public class Main {
	static int N;
	static int[][] dice;
	static int[] idx = {5, 3, 4, 1, 2, 0};
	static int[][] side;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 주사위의 개수
		dice = new int[N][6];		
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<6; j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		
		int max = 0;
		for(int i=0; i<6; i++) {
			side = new int[N][4];
			for(int j=0, s=0; j<6; j++) {
				if(j==i || j==idx[i]) continue;
				side[0][s++] = j;
			}//for
			max = Math.max(max, diceStacking(i, idx[i], 1, 0));			
		}//for

		System.out.print(max);
	}//main

	private static int diceStacking(int top, int bottom, int cur, int sum) {		
		int max=0;		
		for(int i=0; i<4; i++) {
			max = Math.max(max, dice[cur-1][side[cur-1][i]]);
		}//for
		sum += max;
		
		if(cur == N) return sum;
		
		for(int i=0; i<6; i++) {
			if(dice[cur-1][top] == dice[cur][i]) {
				bottom = i;
				top = idx[i];
				break;
			}//if
		}//for
		
		for(int i=0, s=0; i<6; i++) {
			if(i==top || i==bottom) continue;
			side[cur][s++] = i;
		}//for
		
		sum = diceStacking(top, bottom, cur+1, sum);
		return sum;
	}//diceStacking

}//class