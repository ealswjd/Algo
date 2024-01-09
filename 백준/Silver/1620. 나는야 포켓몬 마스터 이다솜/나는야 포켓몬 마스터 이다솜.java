import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1620
public class Main {
	static String[] number;
	static Map<String, Integer> name;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 포켓몬의 개수
		int M = Integer.parseInt(st.nextToken()); // 문제의 개수
		
		number = new String[N+1];
		name = new HashMap<>();
		for(int i=1; i<=N; i++) {
			number[i] = br.readLine();
			name.put(number[i], i);
		}//for
		
		StringBuilder ans = new StringBuilder();
		while(M-->0) {
			String key = br.readLine();
			
			if('1' <= key.charAt(0) && key.charAt(0) <= '9') {
				ans.append(number[Integer.parseInt(key)]).append('\n');
			}else {
				ans.append(name.get(key)).append('\n');				
			}

		}//while

		System.out.print(ans);
	}//main

}//class