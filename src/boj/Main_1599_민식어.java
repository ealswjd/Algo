package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/1599
public class Main_1599_민식어 {
	static int N; // 민식어 단어의 개수
	static String[] words; // 민식어 단어들
	static StringBuilder sb; // 정렬된 답
	static int[] order = new int[27]; // 알파벳 순서
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 민식어 단어의 개수
		words = new String[N]; // 민식어 단어들
		
		for(int i=0; i<N; i++) {
			words[i] = br.readLine();
		}//for
		br.close();
		
		sb = new StringBuilder();
		init(); // 알파벳 순서 세팅
		sort(); // 민식어 단어 정렬
		
		System.out.print(sb);
	}//main
	

	private static void sort() {
		PriorityQueue<Word> pq = new PriorityQueue<>(); // 민식어 단어를 정렬하기 위한 우선순위큐
		
		int[] tmp; // 임시 배열
		int oIndex; // order에 쓰일 인덱스
		for(int i=0; i<N; i++) {
			tmp = new int[words[i].length()]; // 현재 단어길이만큼 배열 생성
			for(int c=0,size=words[i].length(), idx=0; c<size; c++, idx++) {
				oIndex = words[i].charAt(c)-96; // 해당 알파벳의 인덱스 (ex:a=1, b=2)
				if(c+1 < size && words[i].charAt(c) == 'n') { // 현재 알파벳이 n이고 
					if(words[i].charAt(c+1) == 'g') { // 다음 알파벳이 g면
						oIndex = 0; // ng의 순서는 0에 있으므로 인덱스 0으로 바꿔주고
						c++; // g 다음부터 탐색하도록 j증가
					}
				}
				tmp[idx] = order[oIndex]; // 현재 단어의 순서 갱신
			}//for j

			pq.offer(new Word(words[i], tmp)); // 우선순위큐에 담아주기
		}//for i
		
		// 정렬된 단어들 담기  
		while(!pq.isEmpty()) {
			sb.append(pq.poll().word);
			sb.append("\n");
		}//while
		
	}//sort

	// 민식어는 a b k d e g h i l m n ng o p r s t u w y의 순서
	private static void init() {
		String s = "0abkdeghilmnngoprstuwy";
		// a -> 인덱스 1부터 시작
		for(int c='a',i=1; c<='z'; c++, i++) {
			order[i] = s.indexOf(c);
		}
		order[0] = s.indexOf("ng"); // 인덱스0에는 ng의 순서 담아줌 
	}//inti
	
	static class Word implements Comparable<Word>{
		String word; // 민식어 단어
		int[] nums; // 단어 알파벳 순서 
		public Word(String word, int[] nums) {
			super();
			this.word = word;
			this.nums = nums;
		}
		@Override
        public int compareTo(Word o) {
			// i=인덱스 max=둘 중 더 짧은 단어 길이
        	int i=0, max=Math.min(this.nums.length, o.nums.length);
        	// 현재 알파벳의 순서와 o 알파멧의 순서가 다를 때까지 인덱스 증가하며 비교
        	while(i+1 < max && this.nums[i]==o.nums[i]) i++;
        	
        	if(this.nums[i] == o.nums[i]){ // 와일문 빠져나왔는데 둘의 순서가 같다면	
        		if(this.nums.length > max) return 1; // 현재 단어의 길이가 더 길다면 뒤로
        		else return -1; // 더 짧으면 얘가 먼저
        	}//if
        	
        	return this.nums[i] - o.nums[i]; // 오름차순 정렬
        }//compareTo  	
		
	}//Word

}//class


/*

4
abakada
alpabet
tagalog
ako
----
abakada
ako
alpabet
tagalog

*/