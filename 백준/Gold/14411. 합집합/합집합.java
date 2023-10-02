import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14411
public class Main {
	static PriorityQueue<int[]> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 직사각형 수
		pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if(a[0] == b[0]) return b[1] - a[1];
				return b[0] - a[0];
			}//compare
		});
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			pq.offer(new int[] {r, c});
		}//for
		br.close();

		long sum = getSum();
		System.out.print(sum);
	}//main

	private static long getSum() {
		long R = pq.peek()[0], C = pq.poll()[1];
		long sum = R * C;
		long r, c;
		
		while(!pq.isEmpty()) {
			r = pq.peek()[0];
			c = pq.poll()[1];
			if(c <= C) continue;
			sum += r * (c-C);
			C = c;
		}//while

		return sum;
	}//getSum

}//class