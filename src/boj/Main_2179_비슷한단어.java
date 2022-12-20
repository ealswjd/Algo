package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Main_2179_비슷한단어 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // N개의 영단어
		String[] words = new String[N]; // 영단어들
		String[] sWords = new String[N]; // 정렬된 영단어들
		
		for(int i=0; i<N; i++) {
			words[i] = br.readLine();
			sWords[i] = words[i];
		}
		br.close();
		Arrays.sort(sWords); // 정렬
		
		HashMap<String, LinkedHashSet<String>> map = new HashMap<>();
		LinkedHashSet<String> set;
		int max = -1; // 가장 긴 접두사 길이
		// 정렬된 단어 돌리기
		for(int i=0,len=words.length-1; i<len; i++) {
			for(int j=sWords[i].length(); j>0; j--) {
				if(j < max) break; // max보다 접두사 길이가 짧다면 볼 필요 없음
				String s = sWords[i].substring(0, j); // 접두사
				if(sWords[i+1].indexOf(s) == 0 && !map.containsKey(s)) { // 공통된 접두사면
					set = new LinkedHashSet<>();
					map.put(s, set);	// 접두사 저장
					max = Math.max(max, j); // max 접두사 길이 갱신
					break;
				}
			}
		}//for s
		
		String ans="";
		word:for(String word : words) { // 단어 돌려서
			for(String key : map.keySet()) {
				if(word.indexOf(key) == 0) { // 접두사 일치하면
					set = map.get(key);
					set.add(word); // 해당 단어 넣어주고
					if(max == key.length() && set.size()>=2) { // 두 개 다 찾았으면
						ans = key; // 키값 저장
						break word; // 그만
					}					
				}//if
			}//for word
		}//for key
		
		// 정답 출력
		StringBuilder sb = new StringBuilder();
		set = map.get(ans);
		Iterator<String> it = set.iterator();
		for(int i=0; i<2; i++) {
			sb.append(it.next()).append("\n");
		}
		
		System.out.print(sb);

	}//main

}//class

/*

/in/
4
abcd
abe
abc
abchldp

/out/
abcd
abc

*/