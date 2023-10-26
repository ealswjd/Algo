import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2922
public class Main {
	static int len, L;
	static long result;
	static char[] word;
	static final String VOWEL = "AEIOU";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		word = br.readLine().toCharArray(); // 즐겁지 않은 단어
		br.close();
		
		len = word.length;		
		L = 0;
		for(char c : word) {
			if(c == 'L') L++;
		}//for
		makeWord(0, 0, 0, 0, 0);

		System.out.print(result);
	}//main

	private static void makeWord(int cur, int vowel, int consonant, int totalVowel, int totalConsonant) {
		if(vowel>=3 || consonant>=3) return;
		if(cur == len) {
			check(totalVowel, totalConsonant);
			return;
		}//if
		
		// 문자 있는 상태
		if(word[cur] != '_') {
			if(VOWEL.indexOf(word[cur]) > -1) {
				if(vowel>=2) return;
				vowel++;
				consonant = 0;
			}
			else {
				if(consonant>=2) return;
				consonant++;
				vowel = 0;
			}
			makeWord(cur+1, vowel, consonant, totalVowel, totalConsonant);
		}
		else { 
			if(vowel>=2) { // 모음 연속 2개 이상
				word[cur] = 'B'; // 자음으로 바꿈
				makeWord(cur+1, 0, consonant + 1, totalVowel, totalConsonant+1);
			}else if(consonant >= 2) { // 자음 연속 2개 이상
				word[cur] = 'A'; // 모음으로 바꿈
				makeWord(cur+1, vowel + 1, 0, totalVowel+1, totalConsonant);
			}else { // 둘 다 가능
				// 모음
				word[cur] = 'A';
				makeWord(cur+1, vowel + 1, 0, totalVowel+1, totalConsonant);
				// 자음
				word[cur] = 'B';
				makeWord(cur+1, 0, consonant + 1, totalVowel, totalConsonant+1);					
			}
			word[cur] = '_';
		}//else

	}//makeWord

	private static void check(int vowel, int consonant) {
		long cnt = 1;
		
		// L 없음
		if(L==0) {
			if(consonant==0) return;
			else if(consonant==1) cnt *= (long) Math.pow(5, vowel);
			else if(consonant>1){
				cnt *= (long) Math.pow(21, consonant) - (long) Math.pow(20, consonant);
				if(vowel>0) cnt *= (long) Math.pow(5, vowel);
			}
		}else if(L>0){
			if(vowel>0) cnt *= (long) Math.pow(5, vowel);
			if(consonant>0)cnt *= (long) Math.pow(21, consonant);
		}
		
		result += cnt;
	}//check

}//class