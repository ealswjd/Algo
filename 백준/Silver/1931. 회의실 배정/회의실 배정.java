import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1931
public class Main {
	static PriorityQueue<Time> wait;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		wait = new PriorityQueue<>();
		int start, end;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			wait.offer(new Time(start, end));
		}//while
		br.close();

		System.out.print( getCnt() );
	}//main

	private static int getCnt() {
		int cnt = 1;
		Time use = wait.poll();
		Time cur;
		while(!wait.isEmpty()) {
			cur = wait.poll();
			if(use.end <= cur.start) {
				use = cur;
				cnt++;
			}
		}//while
		
		return cnt;
	}//getCnt
	
	static class Time implements Comparable<Time> {
		int start;
		int end;
		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Time t) {
			if(this.end == t.end) return this.start - t.start;
			return this.end - t.end;
		}
	}
}//class