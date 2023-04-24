import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 용액 수
	static int[] arr; // 용액

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Math.abs(o1) - Math.abs(o2);
			}
		});
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 정렬
		for(int i=0; i<N; i++) {
			pq.offer(Integer.parseInt(st.nextToken()));
		}//for
		
		for(int i=0; i<N; i++) {
			arr[i] = pq.poll();
		}//for
		
		System.out.print(getMin());
	}//main

	private static int getMin() {
		int min = Integer.MAX_VALUE;
		int ans = min;
		
		int start = 0;
		int end = start+1;
		
		while(end < N) {
			if(min > Math.abs(arr[start]+arr[end])) {
				min = Math.abs(arr[start]+arr[end]);
				ans = arr[start]+arr[end];
			}
			start++;
			end++;
		}//while
		
		return ans;
	}//getMin

}//class