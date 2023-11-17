import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19598
public class Main {
	static PriorityQueue<Time> times; // 회의 시간들

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 회의 개수
		times = new PriorityQueue<>();
		
		StringTokenizer st;
		int start, end;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken()); // 시작 시간
			end = Integer.parseInt(st.nextToken()); // 종료 시간
			times.offer(new Time(start, end));
		}//while
		br.close();
		
		int cnt = getCnt();
		System.out.print(cnt);
	}//main
	
	private static int getCnt() {
		// 회의 (종료 시간 기준 오름차순)
		PriorityQueue<Time> meeting = new PriorityQueue<>(new Comparator<Time>() {
			@Override
			public int compare(Time o1, Time o2) {
				return o1.end - o2.end;
			}
		});
		// 대기
		PriorityQueue<Time> waiting = new PriorityQueue<>();
		
		Time cur;
		int cnt = 0;
		while(!times.isEmpty()) {
			cur = times.poll();
			waiting.offer(cur); // 일단 대기
			
			// 회의 끝난 팀 종료 시간이 대기 팀 시작시간보다 빠르거나 같으면 -> 현재 회의 팀 회의 종료 
			if(!meeting.isEmpty() && meeting.peek().end <= waiting.peek().start) {
				meeting.poll();
			}
			else cnt++; // 시간 안 맞으면 회의실 새로 추가
			
			// 대기 팀 회의 들어감
			meeting.offer(waiting.poll());
		}//while
		
		return cnt;
	}//getCnt

	static class Time implements Comparable<Time> {
		int start; // 시작 시간
		int end; // 종료 시간
		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Time t) {
			return this.start - t.start; // 시작 시간 기준 오름차순
		}
	}//Time

}//class