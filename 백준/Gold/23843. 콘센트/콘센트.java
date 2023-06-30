import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23843
public class Main {
	static int N, M;
	static PriorityQueue<Integer> pq; // 전자기기

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 전자기기의 개수
		M = Integer.parseInt(st.nextToken()); // 콘센트의 개수
		
		pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		// 충전에 필요한 시간 ti를 나타내는 N개의 정수가 주어진다.
		st = new StringTokenizer(br.readLine());
		br.close();
		for(int i=0; i<N; i++) {
			pq.offer(Integer.parseInt(st.nextToken()));
		}//for
		
		int time = charge();
		System.out.print(time);
	}//main

	private static int charge() {
		PriorityQueue<Integer> charging = new PriorityQueue<>();
		Queue<Integer> tmp = new LinkedList<>();
		int time = 0;
		int prev=0, cur;
		
		while(!pq.isEmpty()) {
			for(int i=0, max=M-charging.size(); i<max && !pq.isEmpty(); i++) {
				charging.add(pq.poll());
			}//for

			prev = charging.peek();
			while(!charging.isEmpty()) {
				cur = charging.poll();
				if(cur-prev > 0) tmp.add(cur-prev);
			}//while
			while(!tmp.isEmpty()) {
				charging.add(tmp.poll());
			}
			time += prev;
		}//while
		
		if(!charging.isEmpty()) {
			while(charging.size() > 1) charging.poll();		
			time += charging.poll();
		}
		return time;
	}//charge

}//class

/*

5 2
1 4 4 8 1
-----------
9

*/