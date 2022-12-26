package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20920
public class Main_20920_영단어암기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = makeWordBook(br, N, M);
		br.close();
		
		System.out.print(sb);
	}//main

	private static StringBuilder makeWordBook(BufferedReader br, int N, int M) throws IOException {
		StringBuilder sb = new StringBuilder();
		HashMap<String, Integer> map = new HashMap<>();
		String key;
		while(N-->0) {
			key = br.readLine();
			if(key.length() < M) continue;
			map.put(key, map.getOrDefault(key, 0)+1);
		}//while
		
		List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
		list.sort(new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if(o1.getValue() != o2.getValue()) return o2.getValue() - o1.getValue();
				else if(o1.getKey().length() != o2.getKey().length()) {
					return o2.getKey().length() - o1.getKey().length();
				}
				else return o1.getKey().compareTo(o2.getKey());
			}
		});
		
		for(Map.Entry<String, Integer> entry : list) {
			sb.append(entry.getKey()).append("\n");
		}
		
		return sb;
	}//makeWordBook

}//class

/*

7 4
apple
ant
sand
apple
append
sand
sand
---out----
sand
apple
append

*/