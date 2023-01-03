package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9935_문자열폭발 {
static String str, target;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		str = br.readLine(); // 문자열 원본
		target = br.readLine(); // 폭발 문자열
		br.close(); 

		int sbSize, len = target.length();
		for(int i=0,max=str.length(); i<max; i++) {
			sb.append(str.charAt(i));
			sbSize = sb.length();
			if(sbSize >= len) {
				boolean flag = true;
				for(int k=0; k<len; k++) {
					if(sb.charAt(sbSize-len+k) != target.charAt(k)) {
						flag = false;
						break;
					}//if
				}//for
				
				if(flag) sb.delete(sbSize-len, sbSize);

			}//if
			
		}//for

		if(sb.length() == 0) System.out.print("FRULA");
		else System.out.print(sb);
	}//main


}//class
