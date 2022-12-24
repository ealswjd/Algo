package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_20437_문자열게임2 {
	static int K, max, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T-->0) {
			String str = br.readLine();
			K = Integer.parseInt(br.readLine());
			
			max = -1;
			min = 10001;
			sol(str);
			if(max != -1) sb.append(min).append(" ").append(max);
			else sb.append(-1);
			
			sb.append("\n");
		}//while

		System.out.print(sb);
	}//main

	private static void sol(String str) {
		int len = str.length();
		int start=0, end=0;
		int cnt=0;
		
		while(start<len) {
			if(str.charAt(start) == str.charAt(end++)) {
				cnt++;
				if(cnt==K) {
					max = Math.max(max, end-start);
					min = Math.min(min, end-start);
					start++;
					end = start;
					cnt = 0;
				}
			} 
			if (end==len) {
				start++;
				end = start;
				cnt = 0;
			}
		}//while
		
	}//sol

}//class

/*

[in]
2
superaquatornado
2
abcdefghijklmnopqrstuvwxyz
5

[out]
4 8
-1

*/