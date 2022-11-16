package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_9663_NQueen {
	static int N, ans;
	static int[] cols;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cols = new int[N+1];
		
		setQueen(1);
		System.out.print(ans);
	}

	private static void setQueen(int row) {
//		if(!isAvailable(row-1)) return;
		if(row > N) {
			ans++;
			return;
		}
		
		for(int i=1; i<=N; i++) {
			cols[row] = i;
			if(isAvailable(row)) setQueen(row+1);
		}
		
	}

	private static boolean isAvailable(int row) {
		for(int i=1; i<row; i++) {
			if(cols[i] == cols[row] || row-i == Math.abs(cols[i]-cols[row])) 
				return false;
		}
		return true;
	}

}
