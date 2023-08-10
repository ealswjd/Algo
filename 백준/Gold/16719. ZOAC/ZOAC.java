import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/16719
public class Main {
	static char[] word;
	static StringBuilder ans;
	static int size;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		word = br.readLine().toCharArray();
		ans = new StringBuilder();
		br.close();
		
		size = word.length;
		ZOAC(0, size-1, new boolean[size]);
		
		System.out.print(ans);
	}//main

	private static void ZOAC(int start, int end, boolean[] checked) {
		if(start > end) return;
		
		int idx = start;
		for(int i=start; i<=end; i++) {
			if(word[idx] > word[i]) idx = i;
		}//for
		checked[idx] = true;
		
		append(checked);
		
		ZOAC(idx+1, end, checked);   // 뒷부분 탐색
		ZOAC(start, idx-1, checked); // 앞부분 탐색
	}//ZOAC

	private static void append(boolean[] checked) {
		for(int i=0; i<size; i++) {
			if(checked[i]) ans.append(word[i]);
		}//for
		ans.append("\n");
	}//append

}//class