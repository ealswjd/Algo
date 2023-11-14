import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1689
public class Main {
	static int N;
	static PriorityQueue<Line> lines;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 선분 개수		
		lines = new PriorityQueue<>();
		
		StringTokenizer st;
		int start, end;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			lines.offer(new Line(start, end));
		}//for
		br.close();

		int max = getMax();
		System.out.print(max);
	}//main
	
	private static int getMax() {
		int max = 0; // 최대로 많이 겹치는 선분들의 개수
		PriorityQueue<Integer> range = new PriorityQueue<>(); // 겹치는 선분들
		
		Line cur;
		while(!lines.isEmpty()) {
			cur = lines.poll(); // 현재 선
			range.offer(cur.end);						
			
			while(!range.isEmpty() && range.peek() <= cur.start) {
				range.poll(); // 안 겹치는 선분 제거
			}//while
			
			max = Math.max(max, range.size());
		}//while		
		
		return max;
	}//getMax

	static class Line implements Comparable<Line> {
		int start;
		int end;
		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Line o) {
			return this.start - o.start;
		}	
	}//Line

}//class