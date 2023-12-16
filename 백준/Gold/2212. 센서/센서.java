import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2212
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine()) - 1;
		
		int sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//for
		br.close();
		
		Arrays.sort(arr);
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int i=1,n=0; i<N; i++) {
			n = arr[i] - arr[i-1];
			sum += n;
			pq.add(n);
		}//for
		
		while(K-->0 && !pq.isEmpty()) {
			sum -= pq.poll();
		}//while

		System.out.print(sum);
	}//main

}//class