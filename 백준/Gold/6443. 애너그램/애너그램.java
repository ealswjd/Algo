import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/6443
public class Main {
	static int N; // 단어의 개수
	static char[] word, result, tmp; // 단어
	static boolean[] visited; // 방문체크
	static StringBuilder ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 단어의 개수
		result = new char[21]; // 만들 수 있는 단어
		tmp = new char[21]; // 중복 체크
		visited = new boolean[21]; 
		ans = new StringBuilder();
		
		while(N-->0) {
			word = br.readLine().toCharArray();
			int len = word.length;
			Arrays.sort(word); // 알파벳 정렬
			comb(0, 0, len); // 단어 만들러
		}//for
		br.close();

		System.out.print(ans);
	}//main

	private static void comb(int start, int cnt, int len) {
		if(cnt == len) {
			for(int i=0; i<len; i++) {
				ans.append(result[i]);				
			}//for
			ans.append("\n");
			return;
		}//if
		
		tmp[cnt] = 0;
		for(int i=0; i<len; i++) {
			if(visited[i]) continue; // 방문체크
			if(tmp[cnt] == word[i]) continue; // 중복체크
			
			tmp[cnt] = word[i];
			visited[i] = true;
			result[cnt] = word[i];
			comb(i+1, cnt+1, len);
			visited[i] = false;			
		}//for
		
	}//comb

}//class