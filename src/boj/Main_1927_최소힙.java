package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1927_최소힙 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();		
		StringBuilder sb = new StringBuilder();
		while(N-->0) {
			int x = Integer.parseInt(br.readLine());
			if(x!=0) pq.offer(x);
			else sb.append(pq.isEmpty() ? 0 : pq.poll()).append("\n");
		}//while
		br.close();
		
		System.out.print(sb);
	}//main

}//class
