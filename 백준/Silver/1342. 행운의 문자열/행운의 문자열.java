import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1342
public class Main {
	static int result, len;
	static int[] cnt;
	static char[] S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().toCharArray(); // 기존 문자열
		br.close();
		
		len = S.length; // 문자열 길이
		cnt = new int[26]; // 문자열에 쓰인 알파벳 개수
		for(char c : S) {
			cnt[c-'a']++;
		}//for
		comb(-1, 0);

		System.out.print(result);
	}//main

	private static void comb(int prev, int cur) {
		if(cur == len) { // 행운의 문자열 완성
			result++;
			return;
		}//if
		
		for(int i=0; i<26; i++) {
            // 문자 사용 못하거나 이전 문자랑 같으면 넘어감
			if(cnt[i] == 0 || prev == i) continue; 
			cnt[i]--; // 문자 사용
			comb(i, cur+1); 
			cnt[i]++; // 문자 사용 취소
		}//for
		
	}//comb

}//class