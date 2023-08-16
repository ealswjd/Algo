import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2562
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int cnt;
		char[] S;
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			cnt = Integer.parseInt(st.nextToken());
			S = st.nextToken().toCharArray();
			for(char c : S) {
				for(int i=0; i<cnt; i++) {
					ans.append(c);
				}//for i
			}//for c
			ans.append("\n");
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

}//class