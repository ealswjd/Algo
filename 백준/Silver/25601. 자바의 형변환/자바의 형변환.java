import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25601
public class Main {
	static int N;
	static Map<String, Integer> keyMap;
	static ArrayList<ArrayList<Integer>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 클래스의 수
		
		init();
		for(int i=0, key=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			String c = st.nextToken();
			String p = st.nextToken();
			if(!keyMap.containsKey(p)) keyMap.put(p, key++);
			if(!keyMap.containsKey(c)) keyMap.put(c, key++);
			list.get(keyMap.get(p)).add(keyMap.get(c));
		}//for
		
		st = new StringTokenizer(br.readLine());		
		int c1 = keyMap.get(st.nextToken());
		int c2 = keyMap.get(st.nextToken());
		br.close();

		int result = solution(c1, c2);
		if(result == 0) solution(c2, c1);
		System.out.print(0);
	}//main

	private static int solution(int cur, int target) {
		if(cur == target) return 1;
		
		int result = 0;
		for(int next : list.get(cur)) {
			result = solution(next, target);
			if(result == 1) {
				System.out.print(1);
				System.exit(0);
			}
		}//for
		
		return result;
	}//solution

	private static void init() {
		keyMap = new HashMap<>();
		list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());	
		}//for		
	}//init

}//class