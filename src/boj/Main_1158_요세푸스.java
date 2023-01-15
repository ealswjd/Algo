package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1158_요세푸스 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 1번부터 N번까지 N명의 사람
		int K = Integer.parseInt(st.nextToken()); // K번째 사람 제거
		StringBuilder sb = new StringBuilder();

		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			q.offer(i);
		}
		
		sb = new StringBuilder();
		sb.append("<");
		while(q.size() > 1) {
			for(int i=1; i<K; i++) {
				q.offer(q.poll());
			}
			sb.append(q.poll()).append(", ");
		}
		sb.append(q.poll()).append(">");
		System.out.println(sb);

	}//main


}//class
