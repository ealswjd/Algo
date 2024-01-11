import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1958
public class Main {
	static char[] A, B, C;
	static int[][][] lcs;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
		C = br.readLine().toCharArray();
		br.close();

		int len = LCS(A.length, B.length, C.length);
		System.out.print(len);
	}//main

	private static int LCS(int lenA, int lenB, int lenC) {
		lcs = new int[lenA+1][lenB+1][lenC+1];
		
		for(int a=1; a<=lenA; a++) {
			for(int b=1; b<=lenB; b++) {
				for(int c=1; c<=lenC; c++) {
					if(A[a-1] == B[b-1] && B[b-1] == C[c-1]) {
						lcs[a][b][c] = lcs[a-1][b-1][c-1] + 1;
					}else {
						lcs[a][b][c] = Math.max(lcs[a-1][b][c]
								, Math.max(lcs[a][b-1][c], lcs[a][b][c-1]));
					}
				}//for c
			}//for b
		}//for a
		
		return lcs[lenA][lenB][lenC];
	}//LCS

}//class