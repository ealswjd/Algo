import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2512
public class Main {
	static int N, M; // 지방의 수 N, 총 예산 M
	static int[] cost; // 각 지방의 예산요청

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 지방의 수
		cost = new int[N]; // 각 지방의 예산요청
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int sum = 0, max = 0;
		for(int i=0; i<N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			sum += cost[i]; // 각 지방의 예산요청 합계
			max = Math.max(max, cost[i]); // 각 지방의 예산요청 최댓값
		}//for
		
		M = Integer.parseInt(br.readLine()); // 총 예산
		br.close();
		
		if(sum > M) max = getMax();
		
		System.out.print(max);
	}//main

	private static int getMax() {
		Arrays.sort(cost);
		int max = 0;
		int start = 1;
		int end = cost[N-1];
		int mid = (start+end) / 2;
		
		int sum;
		while(start <= end) {
			mid = (start+end) / 2;
			sum = getSum(mid);
			if(sum > M) end = mid-1;
			else if(sum < M) {
				start = mid+1;
				max = Math.max(mid, max);
			}else return mid;
		}//while
		
		return max;
	}//getMax

	private static int getSum(int mid) {
		int sum = 0;
		for(int c : cost) {
			if(c  <= mid) sum += c;
			else sum += mid;
		}//cost
		return sum;
	}//getSum

}//class