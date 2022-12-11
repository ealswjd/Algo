package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5430
public class Main_5430_AC {
	static char[] order; // 수행할 함수 
	static Deque<Integer> dq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 케이스의 개수
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(T-->0) {
			order = br.readLine().toCharArray(); // 수행할 함수
			dq = new ArrayDeque<>();
			
			int n = Integer.parseInt(br.readLine()); // 배열에 들어있는 수의 개수
			st = new StringTokenizer(br.readLine(),"[,]");
			for(int i=0; i<n; i++) {
				dq.offer(Integer.parseInt(st.nextToken()));
			}
			
			sb.append(solution());
			sb.append("\n");
		}//while

		System.out.print(sb);
	}//main

	private static String solution() {
		boolean first = true;
		
		for(char C : order) {
			if(C == 'D') { // D(버리기)
				if(dq.isEmpty()) return "error";
				
				if(first) dq.pollFirst();
				else dq.pollLast();
			}else { // R(뒤집기)
				first = first ? false : true;
			}
		}//for
		
		return getResult(first);
	}//solution

	// 수행한 결과를 출력
	private static String getResult(boolean first) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		while(!dq.isEmpty()) {
			if(first) sb.append(dq.poll());
			else sb.append(dq.pollLast());
			
			if(dq.size() >= 1) sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}//getResult

}//class

/*

4
RDD
4
[1,2,3,4]
DD
1
[42]
RRD
6
[1,1,2,3,5,8]
D
0
[]

-----answer------
[2,1]
error
[1,2,3,5,8]
error

*/
