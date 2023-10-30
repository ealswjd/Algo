import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16139
public class Main {
	static char[] S;
	static int A, L, R, len;
	static int[][] cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().toCharArray(); // 문자열 
		len = S.length;
		int Q = Integer.parseInt(br.readLine());
		cnt = new int[len][26];
		getCnt();
		
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		while(Q-->0) {
			st = new StringTokenizer(br.readLine());
			A = st.nextToken().charAt(0);
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			int result = cnt[R][A-'a'];
			if(L>0) result -= cnt[L-1][A-'a'];
			ans.append(result).append("\n");
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

	private static void getCnt() {
		cnt[0][S[0]-'a']++;
		
		for(int i=1; i<len; i++) {
			cnt[i][S[i]-'a']++;
			for(int j=0; j<26; j++) {
				cnt[i][j] += cnt[i-1][j];
			}//for j
		}//for i
		
	}//getCnt

}//class