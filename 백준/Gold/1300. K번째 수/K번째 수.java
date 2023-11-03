import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1300
public class Main {
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		br.close();
		
		int num = getK();
		System.out.print(num);
	}//main

	private static int getK() {
		int start = 1, end = K, mid;
		long cnt;
		
		while(start <= end) {
			mid = (start + end)/2;
			cnt = 0;
			for(int i=1; i<=N; i++) {
				cnt += Math.min(mid/i, N);
			}//for
			if(cnt < K) start = mid+1;
			else end = mid-1;
		}//while
		
		return start;
	}//getK

}//class