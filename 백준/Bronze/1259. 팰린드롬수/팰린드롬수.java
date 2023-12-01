import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static char[] number;
	static boolean[][] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder ans = new StringBuilder();
		int N;
		while(true) {
			number = br.readLine().toCharArray();			
			if(number[0] == '0') break;
			
			N = number.length;
			p = new boolean[N][N];
			
			if(isPalindrome(0, N-1)) ans.append("yes\n");
			else ans.append("no\n");
		}//while

		System.out.print(ans);
	}//main

	private static boolean isPalindrome(int s, int e) {
		if(s >= e) return true;
		
		if(number[s] == number[e]) p[s][e] = isPalindrome(s+1, e-1);
		else p[s][e] = false;
		
		return p[s][e];
	}//isPalindrome

}//class