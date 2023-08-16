import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2562
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}
		});
		
		for(int i=1; i<10; i++) {
			pq.offer(new int[] {i, Integer.parseInt(br.readLine())});
		}//for
		br.close();
		
		StringBuilder ans = new StringBuilder();
		ans.append(pq.peek()[1]).append("\n");
		ans.append(pq.poll()[0]);
		System.out.print(ans);
	}//main

}//class