import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1759
public class Main {
	static int L, C;
	static char[] origin, password;
	static StringBuilder ans;
	static final String aeiou = "aeiou";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken()); // 암호 길이
		C = Integer.parseInt(st.nextToken()); // 암호를 만들 수 있는 문자의 종류
		
		origin = br.readLine().replaceAll(" ", "").toCharArray();
		br.close();
		
		Arrays.sort(origin);
		
		password = new char[L];
		ans = new StringBuilder();
		makePassword(0, 0); // 암호 만들러
		System.out.print(ans);
	}//main

	// 								시작 인덱스, 암호 개수
	private static void makePassword(int start, int cnt) {
		if(cnt == L) { // 암호 완성
			if(!isAvailable()) return; // 가능한 암호인지 확인
			ans.append(String.valueOf(password)).append("\n");
			return;
		}//if
		
		for(int i=start; i<C; i++) {
			password[cnt] = origin[i];
			makePassword(i+1, cnt+1);
		}//for		
	}//makePassword

	private static boolean isAvailable() {
		int vowel = 0; // 모음 개수
		
		for(char c : password) {
			if(aeiou.indexOf(c) != -1) vowel++;
		}//for
		
		// 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성
		return vowel > 0 && L - vowel >= 2;
	}//isAvailable

}//class