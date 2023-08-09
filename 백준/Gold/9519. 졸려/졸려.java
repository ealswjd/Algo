import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// https://www.acmicpc.net/problem/9519
public class Main {
	static int X; // 선영이가 눈을 깜빡인 횟수
	static char[] word;
	static int len;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		X = Integer.parseInt(br.readLine()); // 선영이가 눈을 깜빡인 횟수
		word = br.readLine().toCharArray(); // X번 깜박인 후의 단어
		br.close();
		
		len = word.length;
		getOriginalWord();
	}//main

	private static void getOriginalWord() {
		ArrayList<char[]> orderList = new ArrayList<>();
		orderList.add(word);
		mixWord(orderList);
		
		StringBuilder origin = new StringBuilder();
		int idx = X % orderList.size();
		char[] originWord = orderList.get(idx);
		for(char c : originWord) {
			origin.append(c);
		}
		System.out.print(origin);
	}//getOriginalWord

	private static void mixWord(ArrayList<char[]> orderList) {
		char[] mix = new char[len];
		copyArray(mix, word);	
		
		int half = len / 2;
		int start, end;
		while(true) {
			char[] tmp = new char[len];
			start = 0; end = len-1;
			for(int i=0, b=0; i<len; i++) {
				if(b < half && i%2==1) {
					tmp[end--] = mix[i];
					b++;
				}else {
					tmp[start++] = mix[i];
				}				
			}//for

			if(sameWord(tmp)) break;
			orderList.add(tmp);			
			copyArray(mix, tmp);		
		}//while
		
	}//mixWord

	private static boolean sameWord(char[] tmp) {
		for(int i=0; i<len; i++) {
			if(tmp[i] != word[i]) return false;
		}//for
		return true;
	}//sameWord

	private static void copyArray(char[] temp, char[] origin) {
		for(int i=0; i<len; i++) {
			temp[i] = origin[i];
		}//for		
	}//copyArray

}//class