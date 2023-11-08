import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1202
public class Main {
	static PriorityQueue<Jewel> jewels; // 보석
	static PriorityQueue<Integer> bags; // 가방

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		jewels = new PriorityQueue<>();
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			jewels.offer(new Jewel(M, V));
		}//while
		
		bags = new PriorityQueue<>();
		while(K-->0) {
			bags.offer(Integer.parseInt(br.readLine()));
		}//while
		br.close();
		
		long max = getMax();
		System.out.print(max);
	}//main

	private static long getMax() {
		// 임시 주머니
		PriorityQueue<Integer> tmp = new PriorityQueue<>(Collections.reverseOrder());
		long sum = 0;		
		int bag;		

		while(!bags.isEmpty()) {
			bag = bags.poll(); // 현재 가방 무게
			
			// 임시 주머니에 현재 가방에 담을 수 있는 보석 다 담아
			while(!jewels.isEmpty() && jewels.peek().M <= bag) {
				tmp.offer(jewels.poll().V);
			}//while
			
			if(!tmp.isEmpty()) sum += tmp.poll(); // 보석 무게 더해주기
		}//while
		
		return sum;
	}//getMax

	static class Jewel implements Comparable<Jewel> {
		int M; // 보석 무게
		int V; // 보석 가격
		public Jewel(int M, int V) {
			this.M = M;
			this.V = V;
		}
		@Override
		public int compareTo(Jewel j) {
			return this.M - j.M; // 보석 무게 오름차순
		}
	}//Jewel
}//class