package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16397
public class Main_16397_탈출 {
	static int size = 199999, max=99999;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // LED로 표현된 수
		int T = Integer.parseInt(st.nextToken()); // 버튼을 누를 수 있는 최대 횟수
		int G = Integer.parseInt(st.nextToken()); // 탈출을 위해 똑같이 만들어야 하는 수
		br.close();
		
		visited = new int[size];
		int time = bfs(N, T, G);
		if(time == -1) System.out.print("ANG");
		else System.out.print(time);

	}//main

	private static int bfs(int N, int T, int G) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		visited[N] = 1;
		
		while(!q.isEmpty()) {
			int n = q.poll();
			int cnt = visited[n];
			if(n == G) return cnt-1; // 탈출
			
			// N이 99,999를 넘어가는 순간 탈출에 실패
			if(n > max || cnt > T) continue;
			
			// 버튼 A를 누르면 N이 1 증가한다.
			if( n+1 <= max && (visited[n+1] == 0) || visited[n+1] > cnt+1) {
				q.offer(n+1);
				visited[n+1] = cnt+1;
			}
			// 버튼 B를 누르면 N에 2가 곱해진 뒤, 0이 아닌 가장 높은 자릿수의 숫자가 1 줄어든다
			if(n > 0) {
				int b = n*2;
				if(b > max) continue;
				int sub = 1;
				for(int i=0, num=(int)Math.log10(n*2); i<num; i++) {
					sub *= 10;
				}
				b -= sub;
				if(b <= max && (visited[b] == 0 || visited[b] > cnt+1)) {
					q.offer(b);
					visited[b] = cnt+1;
				}
			}
			
		}//while
		
		return -1;
	}//bfs


}//class

/*

7142 10 7569
Answer: 3

0 99999 99999
Answer: 11198

49997 2 50000
Answer: ANG

1 99999 13
Answer: 10

*/
