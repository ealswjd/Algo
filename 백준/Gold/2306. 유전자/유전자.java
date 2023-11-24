import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/2306
public class Main {
	static char[] dna;
	static int[][] koi;
	static Map<Character, Character> map = new HashMap<>();	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dna = br.readLine().toCharArray();
		int len = dna.length;
		br.close();

		map.put('a', 't');
		map.put('g', 'c');				
		
		koi = new int[len][len];		
		System.out.print(getMax(len));
	}//main

	private static int getMax(int len) {
		int cnt = 0;
		
		while(cnt++ < len) {
			for(int s=0; s+cnt<len; s++) {
				int e = s+cnt;
				
				// 어떤 X가 KOI 유전자라면, aXt와 gXc도 KOI 유전자
				if(map.containsKey(dna[s]) && map.get(dna[s]) == dna[e]) {
					koi[s][e] = koi[s+1][e-1] + 2;
				}//if
				
				// 어떤 X와 Y가 KOI 유전자라면, 이 둘을 연결한 XY도 KOI 유전자
				for(int i=s; i<e; i++) {
					koi[s][e] = Math.max(koi[s][e], koi[s][i] + koi[i+1][e]);
				}//for
				
			}//for s
		}//while
		
		return koi[0][len-1];
	}//getMax

}//class