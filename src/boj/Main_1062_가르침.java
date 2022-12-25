package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1062
public class Main_1062_가르침 {
	static int N, K, max;
	static int[] words;
	static char[] antic = {'a', 'n', 't', 'i', 'c'};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		words = new int[N];
		for(int i=0; i<N; i++) {
			int val = 0;
			char[] tmp = br.readLine().toCharArray();
			for(int j=4; j<tmp.length-4; j++) {
				val |= (1 << (tmp[j]-'a'));				
			}
			words[i] = val;
		}
		br.close();

		int used = 0;
		max = -1;
		if(K < 5) max = 0;
		else if(K >= 26) max = N;
		else {
			for(char c : antic) {
				used |= 1 << (c-'a');
			}
			comb(0, 0, used);			
		}
        
		System.out.print(max);
	}//main

	private static void comb(int start, int cnt, int used) {
		if(cnt == K-5) {
			getMax(used);
			return;
		}//if		
		
		for(int i=start; i<26; i++) {
			if((used & (1 << i)) == 1) continue;
			comb(i+1, cnt+1, (used | (1<<i)));
		}//for
	}//comb

	private static void getMax(int used) {
		int cnt = 0;
		for(int word : words) {
			if((used | word ) == used) cnt++;
		}//for str
		
		max = Math.max(max, cnt);
	}//getMax

}//class

/*
[in]
3 6
antarctica
antahellotica
antacartica

[out]
2

*/