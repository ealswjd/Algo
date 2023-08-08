import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1374
public class Main {
	static PriorityQueue<int[]> wait;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 강의의 개수 N
		
		wait = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] c1, int[] c2) {
				if(c1[0] == c2[0]) return c1[1] - c2[1];
				return c1[0] - c2[0];
			}
		});
		
		StringTokenizer st;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();   // 강의 번호 
			int start = Integer.parseInt(st.nextToken()); // 강의 시작 시간
			int end = Integer.parseInt(st.nextToken());   // 강의 종료 시간
			wait.offer(new int[] {start, end});
		}//while
		br.close();
		
		int min = getRoomCnt();
		System.out.print(min);
	}//main
	
	private static int getRoomCnt() {
		int cnt = 0;		
		PriorityQueue<int[]> inUse = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] c1, int[] c2) {
				return c1[1] - c2[1];
			}
		});
		int available = 0;
		
		int[] cur;
		while(!wait.isEmpty()) {
			cur = wait.poll();
			
			while(!inUse.isEmpty() && cur[0] >= inUse.peek()[1]) {
				available++;
				inUse.poll();
			}//while
			
			if(available > 0) available--;
			else cnt++;

			inUse.offer(cur);			
		}//while
		
		return cnt;
	}//getRoomCnt

}//class