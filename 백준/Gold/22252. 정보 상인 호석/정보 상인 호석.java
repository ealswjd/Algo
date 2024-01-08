import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22252
public class Main {
	static Map<String, PriorityQueue<Integer>> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine()); // 쿼리의 개수
		
		map = new HashMap<>();
		StringTokenizer st;
		int cnt;
		long total = 0;
		while(Q-->0) {
			st = new StringTokenizer(br.readLine());
			
			switch (st.nextToken()) {
			case "1": // 고릴라
				String name = st.nextToken();
				PriorityQueue<Integer> pq = map.getOrDefault(name, new PriorityQueue<>(Collections.reverseOrder()));
				cnt = Integer.parseInt(st.nextToken());
				while(cnt-->0) {
					pq.offer(Integer.parseInt(st.nextToken()));
				}//while
				map.put(name, pq);
				break; 
			case "2": // 호석이
				String key = st.nextToken();
				cnt = Integer.parseInt(st.nextToken());
				if(!map.containsKey(key)) break;
				while(!map.get(key).isEmpty() && cnt > 0) {
					cnt--;
					total += map.get(key).poll();
				}
				break;
			}//switch
			
		}//while
		br.close();

		System.out.print(total);
	}//main

}//class