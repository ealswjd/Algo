import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 문제 이름(난이도) : 비슷한 단어 (GOL4)
 * 링크 : https://www.acmicpc.net/problem/2179
 * */
public class Main {
	static int N; // 단어의 수
	static String[] wordArr; // N개의 영단어들
	static Word[] words;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		wordArr = new String[N];
		words = new Word[N];
		for(int i=0; i<N; i++) {
			wordArr[i] = br.readLine();
			words[i] = new Word(i, wordArr[i]);
		}//for
		br.close();
		Arrays.sort(words);

		getSimilarWords();
	}//main
	
	private static void getSimilarWords() {
		PriorityQueue<PrefixInfo> similar = new PriorityQueue<>();

		Word cur, prev;
		int max = -1, sIdx, tIdx, jMax;
		for(int i=0,len=words.length-1; i<len; i++) {
			prev = words[i];
			for(int c=i+1; c<N; c++) {
				cur = words[c];
				jMax = prev.word.length() < cur.word.length() 
						? prev.word.length() : cur.word.length();
				for(int j=0; j<jMax; j++) {
					while(jMax > j && prev.word.charAt(j) == cur.word.charAt(j)) j++;
					if(j < max) break;
					String s = prev.word.substring(0, j);
					if(cur.word.indexOf(s) == 0 ) {
						max = Math.max(max, j);
						sIdx = prev.idx < cur.idx ? prev.idx : cur.idx;
						tIdx = prev.idx > cur.idx ? prev.idx : cur.idx;
						similar.offer(new PrefixInfo(j, sIdx, tIdx));
						break;
					}
				}//for j				
			}
		}//for	
		
		PrefixInfo info = similar.poll();
		StringBuilder ans = new StringBuilder();
		ans.append(wordArr[info.sIdx]).append("\n");
		ans.append(wordArr[info.tIdx]);
		System.out.print(ans);
	}//getSimilarWords


	static class PrefixInfo implements Comparable<PrefixInfo>{
		int length;
		int sIdx;
		int tIdx;
		public PrefixInfo(int length, int sIdx, int tIdx) {
			this.length = length;
			this.sIdx = sIdx;
			this.tIdx = tIdx;
		}
		@Override
		public int compareTo(PrefixInfo p) {
			if(this.length != p.length) return p.length - this.length;
			else if(this.sIdx != p.sIdx) return this.sIdx - p.sIdx;
			return this.tIdx - p.tIdx;
		}		
	}
	
	static class Word implements Comparable<Word>{
		int idx;
		String word;
		public Word(int idx, String word) {
			this.idx = idx;
			this.word = word;
		}
		@Override
		public int compareTo(Word o) {
			return this.word.compareTo(o.word);
		}	
	}//Word

}//class