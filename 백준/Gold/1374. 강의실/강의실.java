import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1374
public class Main {
	static PriorityQueue<ClassInfo> wait;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 강의의 개수 N
		
		wait = new PriorityQueue<>(new Comparator<ClassInfo>() {
			@Override
			public int compare(ClassInfo c1, ClassInfo c2) {
				if(c1.start == c2.start) return c1.end - c2.end;
				return c1.start - c2.start;
			}
		});
		
		StringTokenizer st;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());   // 강의 번호 
			int start = Integer.parseInt(st.nextToken()); // 강의 시작 시간
			int end = Integer.parseInt(st.nextToken());   // 강의 종료 시간
			wait.offer(new ClassInfo(num, start, end));
		}//while
		br.close();
		
		int min = getRoomCnt();
		System.out.print(min);
	}//main
	
	private static int getRoomCnt() {
		int cnt = 0;		
		PriorityQueue<ClassInfo> inUse = new PriorityQueue<>();
		PriorityQueue<ClassInfo> available = new PriorityQueue<>();
		
		ClassInfo cur;
		while(!wait.isEmpty()) {
			cur = wait.poll();
			
			while(!inUse.isEmpty() && cur.start >= inUse.peek().end) {
				available.offer(inUse.poll());
			}//while
			
			if(available.isEmpty()) cnt++;
			else available.poll();

			inUse.offer(cur);			
		}//while
		
		return cnt;
	}//getRoomCnt

	static class ClassInfo implements Comparable<ClassInfo> {
		int num;
		int start;
		int end;
		public ClassInfo(int num, int start, int end) {
			this.num = num;
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(ClassInfo c) {			
			return this.end - c.end;
		}
	}

}//class