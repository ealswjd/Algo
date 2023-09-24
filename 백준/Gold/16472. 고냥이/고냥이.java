import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/16472
public class Main {
	static int N, size, max;
	static int[] line;
	static int[] alphabetCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		char[] input = br.readLine().toCharArray();
		br.close();
		size = input.length;
		
		alphabetCnt = new int[26];
		line = new int[size];
		for(int i=0; i<size; i++) {
			line[i] = input[i] - 'a';
		}//for
		
		max = 0;
		getMax();
		
		System.out.print(max);		
	}//main

	private static void getMax() {
		int start = 0;
		int end = 0;
		int sum = 0, cnt = 0;
		
		while(end < size) {
			if(alphabetCnt[line[end]] == 0) cnt++;
			alphabetCnt[line[end]]++;
			
			sum++;
			end++;
			if(cnt <= N) {
				max = Math.max(max, sum);				
			}else {				
				while(cnt > N) {
					alphabetCnt[line[start]]--;
					if(alphabetCnt[line[start++]] == 0) cnt--;
					if(start >= end) break;
					sum--;
				}//while
			}//else
			
		}//while
		
	}//getMax

}//class