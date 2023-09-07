import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2602
public class Main {
	static int M, L;
	static char[] magic, devil, angel;
	static int[][][] cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		magic = br.readLine().toCharArray(); // 마법의 두루마리에 적힌 문자열
		devil = br.readLine().toCharArray(); // <악마의 돌다리>
		angel = br.readLine().toCharArray(); // <천사의 돌다리>
		br.close();
		
		M = magic.length; // 마법의 두루마리에 적힌 문자열 길이
		L = devil.length; // 다리 길이
		cnt = new int[M][L][2];
		for(int i=0; i<M; i++) {
			for(int j=0; j<L; j++) {
				Arrays.fill(cnt[i][j], -1);
			}//for
		}//for
		
		int result = 0;
		for(int i=0; i<L; i++) {
			if(devil[i] == magic[0]) result += getCnt(i, 0, 0, devil, angel);
			if(angel[i] == magic[0]) result += getCnt(i, 0, 1, angel, devil);
		}//for
		
		System.out.print(result);
	}//main

	private static int getCnt(int start, int m, int isDevil, char[] from, char[] to) {
		if(cnt[m][start][isDevil] != -1) return cnt[m][start][isDevil];
		if(m+1 == M) return 1;
		
		cnt[m][start][isDevil] = 0;
		for(int i=start+1; i<L; i++) {
			if(to[i] == magic[m+1]) {
				cnt[m][start][isDevil] += getCnt(i, m+1, (isDevil+1)%2, to, from);
			}//if
		}//for
		
		return cnt[m][start][isDevil];
	}//getCnt

}//class