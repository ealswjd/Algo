package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13549_숨바꼭질3 {
	static int N, K;
	static int size = 100001;
	static int[] checked;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
		K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치
		br.close();
		
		checked = new int[size];

		System.out.println( bfs() );
	}//main

	private static int bfs() {
		checked[N] = 1;
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		
		int x;
		while(!q.isEmpty()) {
			x = q.poll();
			if(x == K) return checked[K]-1;
			
			if(x*2 < size && (checked[x*2]==0 || checked[x*2] > checked[x])) {
				q.offer(x*2);
				checked[x*2] = checked[x];
			}
			
			if(x-1 >= 0 && (checked[x-1]==0 || checked[x-1] > checked[x]+1)) {
				q.offer(x-1);
				checked[x-1] = checked[x]+1;
			}
			
			if(x+1 < size && (checked[x+1]==0 || checked[x+1] > checked[x]+1)) {
				q.offer(x+1);
				checked[x+1] = checked[x]+1;
			}
			
		}//while
		
		return 0;
	}//bfs

}//class
