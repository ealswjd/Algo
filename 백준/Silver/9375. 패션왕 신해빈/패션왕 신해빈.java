import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/9375
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		Map<String, Integer> map;		
		StringBuilder ans = new StringBuilder();
		while(T-->0) {
			map = new HashMap<>();
			int N = Integer.parseInt(br.readLine()); // 의상 개수
			
			while(N-->0) {
				String key = br.readLine().split(" ")[1];
				map.put(key, map.getOrDefault(key, 0) + 1);				
			}//while
			
			ans.append(getCnt(map)).append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static int getCnt(Map<String, Integer> map) {
		int cnt = 1;		
		for(String key : map.keySet()) {
			cnt *= map.get(key)+1;
		}//for
		
		return cnt-1;
	}//getCnt

}//class