import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1766
public class Main {
	static int N;
	static ArrayList<ArrayList<Integer>> list;
	static int[] edgeCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
				
		init();
		
		int a, b;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			edgeCnt[b]++;
		}//while
		br.close();

		getOrder();
	}//main

	private static void getOrder() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=1; i<=N; i++) {
			if(edgeCnt[i] == 0) pq.offer(i);
		}//for
		
		StringBuilder ans = new StringBuilder();
		int cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			ans.append(cur).append(" ");
			
			for(int next : list.get(cur)) {
				edgeCnt[next]--;
				if(edgeCnt[next] == 0) pq.offer(next);				
			}//for
		}//while
		
		System.out.print(ans);
	}//getOrder

	private static void init() {
		edgeCnt = new int[N+1];
		list = new ArrayList<>();		
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}//for		
	}//init

}//class