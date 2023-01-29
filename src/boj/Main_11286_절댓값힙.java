package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// https://www.acmicpc.net/problem/11286
public class Main_11286_절댓값힙 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        
		Queue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int a = Math.abs(o1), b = Math.abs(o2);
				if(a == b) return o1 - o2;
				return a-b;
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if(x!=0) q.offer(x); // 0이 아니면 x추가
			else { // 0이라면
				if(q.isEmpty()) sb.append(0); // 비어 있는 경우 0 append
				else sb.append(q.poll()); // 절댓값이 가장 작은 값을 append
				sb.append("\n");
			}			
		}//for
		br.close();
		System.out.println(sb);		

	}//main

}//class
