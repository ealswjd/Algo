import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14719
public class Main {
	static int H, W;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = getIntToken(st); // 2차원 세계의 세로 길이
		W = getIntToken(st); // 2차원 세계의 가로 길이
		
		st = new StringTokenizer(br.readLine());
		int sum = sol(st);
		br.close();

		// 고이는 빗물의 총량을 출력하여라.
		System.out.print(sum);
	}//main

	private static int sol(StringTokenizer st) {
		Deque<Integer> dq = new ArrayDeque<>(); // 2차원 세계
		int sum = 0; // 빗물의 총량
		int h = 0; // 현재 블록 높이

		for(int i=0; i<W; i++) {
			h = getIntToken(st);
			if(dq.isEmpty()) { // 비어 있으면
				if(h==0) continue;
				else dq.offer(h);
			}else { // 비어 있지 않다면
				if(dq.size() == 1 && (dq.peek() == h || dq.peek() < h)) {
					dq.poll();
				}else {
					if(dq.peek() > h && dq.peekLast() < h){
						int n = h - dq.peekLast();
						sum += n;
						dq.offer(n + dq.pollLast());
					}else if(dq.peek() <= h) {
						sum += work(dq, 0);
					}					
				}//else
				dq.offer(h);
			}//else
			
		}//for
		
		sum += work(dq, 1);
		return sum;
	}//sol

	private static int work(Deque<Integer> dq, int order) {
		int sum = 0;
		
		if(order==0) { // 앞에서부터
			while(!dq.isEmpty()) sum += dq.peek() - dq.pollLast();			
		}else { // 뒤에서부터
			int n = dq.pollLast();
			while(dq.size() > 1) {
				if(n > dq.peekLast()) sum += n - dq.pollLast();
				else n = dq.pollLast();
			}
		}
		
		return sum;
	}//work

	private static int getIntToken(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}//getIntToken

}//class