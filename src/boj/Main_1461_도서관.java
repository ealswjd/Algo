package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1461_도서관 {	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = getInt(st.nextToken()); // 책의 개수 N
		int M = getInt(st.nextToken()); // 한 번에 들 수 있는 책의 개수 M
		
		st = new StringTokenizer(br.readLine());
		int cnt = move(N, M, st);		
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
		br.close();

	}//main

	private static int move(int N, int M, StringTokenizer st) {
		int[] positions = new int[N]; // 책의 위치
		int cnt = 0;
		int minus=0;
		for(int i=0; i<N; i++) {
			positions[i] = getInt(st.nextToken());
			if(positions[i] < 0) minus++;
		}//for
		Arrays.sort(positions);
		
		int max = Math.abs(positions[N-1]);
		if(max < Math.abs(positions[0])) max = Math.abs(positions[0]);

		// 음수
		for(int i=0, n=0; i<minus; i+=M) {
			n = Math.abs(positions[i]);
			cnt += n*2;
		}
		// 양수
		for(int i=N-1, n=0; i>=minus; i-=M) {
			n = Math.abs(positions[i]);
			cnt += n*2;
		}
		
		return cnt-max;
	}//move

	private static int getInt(String str) {
		return Integer.parseInt(str);
	}//getInt

}//class

/*

7 2
-37 2 -6 -39 -29 11 -28
---------
131

*/