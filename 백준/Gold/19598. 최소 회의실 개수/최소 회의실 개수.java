import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19598
public class Main {
	static PriorityQueue<Time> times;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		times = new PriorityQueue<>();
		
		StringTokenizer st;
		int start, end;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			times.offer(new Time(start, end));
		}//while
		br.close();
		
		int cnt = getCnt();
		System.out.print(cnt);
	}//main
	
	private static int getCnt() {
		PriorityQueue<Time> meeting = new PriorityQueue<>(new Comparator<Time>() {
			@Override
			public int compare(Time o1, Time o2) {
				return o1.end - o2.end;
			}
		});
		PriorityQueue<Time> waiting = new PriorityQueue<>();
		
		Time cur;
		int cnt = 0;
		while(!times.isEmpty()) {
			cur = times.poll();
			waiting.offer(cur);
			
			if(!meeting.isEmpty() && meeting.peek().end <= waiting.peek().start) meeting.poll();
			else cnt++;
			
			meeting.offer(waiting.poll());
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
			return this.start - t.start;
		}
	}//Time

}//class