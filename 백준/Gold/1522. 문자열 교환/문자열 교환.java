import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1522
public class Main {
	static char[] str;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toCharArray();
		br.close();

		int min = getCnt();
		System.out.print(min);
	}//main

	private static int getCnt() {
		int len = str.length;
		int min = len;
		int aCnt = 0;
		
		for(char c : str) {
			if(c == 'a') aCnt++;
		}//for
		
		for(int i=0; i<len; i++) {
			int bCnt = 0;
			for(int j=i; j<i+aCnt; j++) {
				if(str[(j + len) % len] == 'b') bCnt++;
			}//for
			min = Math.min(min, bCnt);
		}//for
		
		return min;
	}//getCnt

}//class