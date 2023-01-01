package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://www.acmicpc.net/problem/11279
public class Main_11279_최대힙 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 연산의 개수
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		
		int x;
		StringBuilder sb = new StringBuilder();
		while(N-->0) {
			x = Integer.parseInt(br.readLine());
			if(x==0) sb.append(pq.isEmpty() ? "0" : pq.poll()).append("\n");
			else pq.offer(x);
		}//while
		br.close();

		System.out.print(sb);
	}//main

}//class
