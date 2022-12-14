package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12833
public class Main_12833_XOR {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken()) % 2;
		br.close();
		
		int ans = A;
		if(C == 1) {
			ans = (A ^ B);
		}

		System.out.print(ans);
	}//main

}//class

/*

13 3 1
out : 14

*/