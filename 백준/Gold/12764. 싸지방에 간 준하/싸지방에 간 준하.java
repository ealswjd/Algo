import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12764
public class Main {
	static PriorityQueue<Time> wait; // 대기중

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int N = Integer.parseInt(br.readLine()); // 사람의 수
		
		wait = new PriorityQueue<>();
		StringTokenizer st;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			wait.offer(new Time(start, end));
		}//while

		getCnt();
	}//main
	
	private static void getCnt() {		
		int[] computer = new int[100000]; // 컴퓨터
		PriorityQueue<Info> inUse = new PriorityQueue<>(); // 사용중
		// 사용 가능 
		PriorityQueue<Info> available = new PriorityQueue<>(new Comparator<Info>() {
			@Override
			public int compare(Info o1, Info o2) {
				return o1.num - o2.num;
			}
		});
		
		int cnt = 0;		
		Time cur;
		Info use;
		while(!wait.isEmpty()) {
			// 현재 컴퓨터 시간이 된 사람
			cur = wait.poll();
			// 사용중인 사람중에 종료시간이 된 사람이 있다면
			while(!inUse.isEmpty() && inUse.peek().end <= cur.start) {
				// 그 사람 사용 종료, 사용가능중인 컴퓨터 추가
				available.offer(inUse.poll());
			}
			// 사용 가능한 컴퓨터가 있다면
			if(!available.isEmpty()) {
				use = available.poll();
				use.end = cur.end;
				inUse.offer(use);
				computer[use.num]++;
			}else {
				// 사용 가능한 컴퓨터가 없다면 새로운 좌석에 앉음.
				computer[cnt]++;
				inUse.offer(new Info(cnt++, cur.end));
			}
		}//while
		
		StringBuilder answer = new StringBuilder();
		answer.append(cnt).append("\n");
		for(int i=0; i<cnt; i++) {
			answer.append(computer[i]).append(" ");
		}//for
		
		System.out.print(answer);		
	}//sit
	
	static class Info implements Comparable<Info> {
		int num;
		int end;
		public Info(int num, int end) {
			this.num = num;
			this.end = end;
		}
		@Override
		public int compareTo(Info i) {
			return this.end - i.end;
		}
	}

	static class Time implements Comparable<Time>{
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
	}

}//class