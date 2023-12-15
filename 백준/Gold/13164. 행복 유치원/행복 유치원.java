import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13164
public class Main {
	static int N, K, cost;
	static int[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(st.nextToken());
		for(int i=1; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			pq.offer(arr[i]-arr[i-1]);
			cost += arr[i]-arr[i-1];
		}//for
		br.close();
		
		K--;
		while(K-->0) {
			cost -= pq.poll();
		}//while
		
		System.out.print(cost);
	}//main

}//class