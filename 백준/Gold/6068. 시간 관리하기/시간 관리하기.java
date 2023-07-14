import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6068
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 일의 개수
		StringTokenizer st;
		PriorityQueue<Time> pq = new PriorityQueue<>();
		
		int workTime, endTime;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			workTime = Integer.parseInt(st.nextToken());
			endTime = Integer.parseInt(st.nextToken());
			pq.offer(new Time(workTime, endTime));
		}//while
		br.close();			
		
		int total = 0;
		int time = 100000000; // 가장 늦게 일어나도 되는 시간
		while(!pq.isEmpty()) {
			workTime = pq.peek().workTime;
			endTime = pq.poll().endTime;
			total += workTime;			
			if(total > endTime) {
				time = -1; // 제시간에 일을 끝낼 수 없다면 -1
				break;
			}//if
            time = Math.min(time, endTime - total);
		}//while

		System.out.print(time);
	}//main
	
	static class Time implements Comparable<Time> {
		int workTime;
		int endTime;
		public Time(int workTime, int endTime) {
			this.workTime = workTime;
			this.endTime = endTime;
		}		
		@Override
		public int compareTo(Time o) {
			if(this.endTime == o.endTime) return this.workTime - o.workTime;
			return this.endTime - o.endTime;
		}		
	}//Time

}//class