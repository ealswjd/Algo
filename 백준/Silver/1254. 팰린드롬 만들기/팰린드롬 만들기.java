import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1254
public class Main {
	static char[] str;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toCharArray();
		br.close();
		
		int len = str.length;		
		int cnt = 0;
		
		for(int i=0; i<len; i++) {
			if(isPalindrome(i, len-1)) break;
			cnt++;
		}//for
		
		System.out.println(len+cnt);
	}//main

	private static boolean isPalindrome(int s, int e) {
		if(s >= e) return true;
		
		if(str[s] == str[e]) return isPalindrome(s+1, e-1);
		
		return false;
	}//isPalindrome


}//class