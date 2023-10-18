import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2879
public class Main {
	static int N;
	static int[] tab, target;
	static Queue<Line> wait;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 줄의 개수
		
		tab = new int[N]; // 현재 탭 상황
		target = new int[N]; // 올바른 탭의 개수
		wait = new LinkedList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			tab[i] = Integer.parseInt(st.nextToken());
		}//for	
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			target[i] = Integer.parseInt(st.nextToken());
			wait.offer(new Line(i, target[i] - tab[i] > 0, Math.abs(target[i] - tab[i])));
			target[i] = Math.abs(target[i] - tab[i]);
		}//for	
		br.close();

		int cnt = getMinCnt();
		System.out.print(cnt);
	}//main

	private static int getMinCnt() {
		Queue<Line> group = new LinkedList<>();
		
		Line cur;
		int sum=0;
		boolean plus;
		while(!wait.isEmpty()) {
			cur = wait.poll();
			plus = cur.plus;
			group.offer(cur);
			
			while(!wait.isEmpty() && plus==wait.peek().plus&&wait.peek().cnt>0) {
				group.offer(wait.poll());
			}//while
			
			int prevCnt = group.poll().cnt;
			while(!group.isEmpty()) {
				cur = group.poll();
				if(cur.cnt < prevCnt) sum += prevCnt - cur.cnt;
				prevCnt = cur.cnt;
			}//while
			sum += prevCnt;
		}//while
		
		return sum;
	}//getMinCnt
	
	static class Line{
		int idx;
		boolean plus;
		int cnt;
		public Line(int idx, boolean plus, int cnt) {
			this.idx = idx;
			this.plus = plus;
			this.cnt = cnt;
		}
	}//Line

}//class