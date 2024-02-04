import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/17609
public class Main {
	static char[] word;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder ans = new StringBuilder();
		int cnt;
		while(T-->0) {
			word = br.readLine().toCharArray();
			cnt = getCnt(0, word.length-1, 0);
			ans.append(cnt).append('\n');
		}//while		

		System.out.print(ans);
	}//main

	private static int getCnt(int s, int e, int cnt) {
		if(s > e || cnt > 1) return cnt;
		
		if(word[s] == word[e]) cnt = getCnt(s+1, e-1, cnt);
		else {
			cnt = Math.min(getCnt(s, e-1, cnt+1), getCnt(s+1, e, cnt+1));
		}
		return cnt;
	}//getCnt

}//class