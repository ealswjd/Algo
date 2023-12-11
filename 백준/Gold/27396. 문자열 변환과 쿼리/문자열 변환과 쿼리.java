import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27396
public class Main {
	static char[] str; // 문자열
	static char[] cArr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		str = st.nextToken().toCharArray();
		int N = Integer.parseInt(st.nextToken());
		
		cArr = new char[130];
		for(char c='A'; c<='z'; c++) cArr[c] = c;
		
		StringBuilder ans = new StringBuilder();
		int n;
		char a, b;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			switch (n) {
			case 1: // 문자 변경
				a = st.nextToken().charAt(0);
				b = st.nextToken().charAt(0);
				for(int i='A'; i<='z'; i++) {
					if(cArr[i] == a) cArr[i] = b;
				}//for
				break;
			case 2: // 문자 출력
				for(char c : str) {
					ans.append(cArr[c]);
				}//for
				ans.append("\n");
				break;
			}//switch
		}//while
		br.close();

		System.out.print(ans);
	}//main


}//class