import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1092
public class Main {
	static int N, M;
	static int[] box, crane;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 크레인
		N = Integer.parseInt(br.readLine());
		crane = new int[N];
		init(br, N, crane);		
		
		// 박스
		M = Integer.parseInt(br.readLine());
		box = new int[M];
		init(br, M, box);
		
		br.close();

		System.out.print( move() );		
	}//main
	

	private static int move() {				
		if(box[M-1] > crane[N-1]) return -1;
		
		PriorityQueue<Count> pq = new PriorityQueue<>();
		
		int time = 1, idx = N-1;
		for(int i=M-1; i>=0; i--) {
			if(idx >= 0 && box[i] <= crane[idx]) {
				pq.offer(new Count(1));
				idx--;
			}else {
				Count c = pq.poll();
				c.cnt++;
				time = Math.max(time, c.cnt);
				pq.offer(c);
			}
		}//for
		
		return time;
	}//move

	private static void init(BufferedReader br, int size, int[] arr) throws IOException {		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<size; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//for	
		Arrays.sort(arr);
	}//init

	static class Count implements Comparable<Count>{
		int cnt;
		
		public Count(int cnt) {
			super();
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Count o) {
			return this.cnt - o.cnt;
		}
		
	}//Count

}//class

