import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27396
public class Main {
	static char[] str; // 문자열
	static Map<Character, Queue<Integer>> map;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new HashMap<>(); // 문자 인덱스 저장
		initMap('A', 'Z');
		initMap('a', 'z');
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		str = st.nextToken().toCharArray();
		int N = Integer.parseInt(st.nextToken());
		
		for(int i=0, len=str.length; i<len; i++) {
			map.get(str[i]).add(i);
		}//for
		
		StringBuilder ans = new StringBuilder();
		int n;
		char a, b;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			switch (n) {
			case 1: // 문자 변경
				a = st.nextToken().charAt(0);
				b = st.nextToken().charAt(0);
				change(a, b);
				break;
			case 2: // 문자 출력
				ans.append(str).append("\n");
				break;
			}//switch
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static void change(char a, char b) {
		if(a == b) return;
		Queue<Integer> q = map.get(a);
		
		int idx;
		while(!q.isEmpty()) {
			idx = q.poll();
			str[idx] = b;
			map.get(b).add(idx);
		}//while
		
	}//change

	private static void initMap(char start, char end) {
		for(char c=start; c<=end; c++) {
			map.put(c, new LinkedList<>());
		}//for		
	}//initMap

}//class