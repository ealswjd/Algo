package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2195_문자열복사 {
	static String S, P;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		P = br.readLine();
		br.close();
		
		getCount();
		System.out.print(ans);
		
	}//main

	private static void getCount() {
		int pLen = P.length();
		int cnt = 1, start=0;
		for(int i=1; i<=pLen; i++) {
			if(S.indexOf(P.substring(start, i)) == -1) {
				cnt++;
				start = i-1;
				i=start;
			}
		}//for i
		ans = cnt;
	}//getCount
	

}//class
