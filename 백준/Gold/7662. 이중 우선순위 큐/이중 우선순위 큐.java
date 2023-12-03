import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final String EMPTY = "EMPTY\n";
	static PriorityQueue<Integer> minQ, maxQ;
	static Map<Integer, Integer> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int k, n;
		char c;
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		while(T-->0) {
			k = Integer.parseInt(br.readLine());
			init();
			
			while(k-->0) {
				st = new StringTokenizer(br.readLine());
				c = st.nextToken().charAt(0);
				n = Integer.parseInt(st.nextToken());
				work(c, n);
			}//while
			
			while(!maxQ.isEmpty() && map.get(maxQ.peek()) == 0) {
				maxQ.poll();
			}
			while(!minQ.isEmpty() && map.get(minQ.peek()) == 0) {
				minQ.poll();
			}
			
			if(maxQ.isEmpty() || minQ.isEmpty()) ans.append(EMPTY);
			else {
				ans.append(maxQ.poll()).append(" ").append(minQ.poll());
				ans.append("\n");
			}
		}//while

		System.out.print(ans);
	}//main

	private static void work(char c, int n) {
		
		switch (c) {
		case 'I': // 삽입
			minQ.offer(n);
			maxQ.offer(n);
			map.put(n, map.getOrDefault(n, 0) + 1);
			break;
			
		case 'D': // 삭제
			if(n == 1) delete(n, maxQ);	// 최댓값 삭제
			else delete(n, minQ); // 최솟값 삭제
			
			break;
		}//switch
		
	}//work

	private static void delete(int n, PriorityQueue<Integer> pq) {
		
		while(!pq.isEmpty() && map.get(pq.peek()) == 0) {
			pq.poll();
		}//while
		
		if(pq.isEmpty()) return;	
		
		int key = pq.peek();
		map.put(key, map.get(key)-1);			
		
		if(map.get(key) == 0) {
			while(!minQ.isEmpty() && minQ.peek().equals(key)) minQ.poll();
			while(!maxQ.isEmpty() && maxQ.peek().equals(key)) maxQ.poll();
		}
	}//delete

	private static void init() {
		map = new HashMap<>();		
		minQ = new PriorityQueue<>();
		maxQ = new PriorityQueue<>(Collections.reverseOrder());
	}//init

}//class