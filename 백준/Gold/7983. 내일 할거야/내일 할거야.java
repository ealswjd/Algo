import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7983
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 과제 개수
		StringTokenizer st;
		PriorityQueue<Work> pq = new PriorityQueue<>();
		
		int day, end;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			pq.offer(new Work(day, end));
		}//while
		br.close();			
		
		int total = 0;
		int cnt = 1000000000; // 최대 며칠 동안 놀 수 있는지
		Work work;
		while(!pq.isEmpty()) {
			work = pq.poll();
			day = work.day;
			end = work.end;
			total += day;
			cnt = Math.min(cnt, end - total);
		}//while

		System.out.print(cnt);
	}//main
	
	static class Work implements Comparable<Work> {
		int day;
		int end;
		public Work(int day, int end) {
			this.day = day;
			this.end = end;
		}		
		@Override
		public int compareTo(Work w) {
			if(this.end == w.end) return this.day - w.day;
			return this.end - w.end;
		}		
	}//Work

}//class