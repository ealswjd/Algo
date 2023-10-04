import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1135
public class Main {
	static int N; // 직원의 수
	static ArrayList<ArrayList<Integer>> list; // 부하 정보

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());
		}//for
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextToken();
		for(int i=1; i<N; i++) {
			list.get(Integer.parseInt(st.nextToken())).add(i);
		}//for
		br.close();
		
		int time = getTime();		
		System.out.print(time);
	}//main

	private static int getTime() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		int time, max;
		int[] dp = new int[N];
		
		for(int i=N-1; i>=0; i--) {
			if(list.get(i).size() == 0) continue; // 부하직원 없음
			
			time = 1;
			max = 0;
			for(int num : list.get(i)) {
				pq.offer(new Node(num, dp[num]));
			}//for
			
			while(!pq.isEmpty()) {
				max = Math.max(max, pq.poll().time + time++);
			}//while
			dp[i] = max;
		}//for
		
		return dp[0];
	}//getTime

	static class Node implements Comparable<Node>{
		int num;
		int time;
		public Node(int num, int time) {
			this.num = num;
			this.time = time;
		}
		@Override
		public int compareTo(Node o) {
			return o.time - this.time;
		}			
	}//Node
}//class