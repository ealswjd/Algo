import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/1339
public class Main {
	static int N;
	static int[] alpha;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 단어 개수
		alpha = new int[26];
		
		for(int i=0; i<N; i++) {
			char[] word = br.readLine().toCharArray();
			for(int j=0, len=word.length; j<len; j++) {
				alpha[word[j]-'A'] += (int) Math.pow(10, len-j-1);
			}//for
		}//for
		br.close();

		int sum = convertToNumber();
		System.out.print(sum);
	}//main		

	private static int convertToNumber() {
		Arrays.sort(alpha);
		int sum = 0;
		int num = 9;
		for(int i=25; i>=0; i--) {
			if(alpha[i] == 0) continue;
			sum += alpha[i] * num--;
		}//for
		
		return sum;
	}//convertToNumber

}//class