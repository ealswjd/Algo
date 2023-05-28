import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M; // 수의 개수 N과 합을 구해야 하는 횟수 M
	static int[] numbers, sumArr; // N개의 수, 누적합

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		N = Integer.parseInt(st.nextToken()); // 수의 개수
		M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수
		numbers = new int[N];  // 수
		sumArr = new int[N+1]; // 누적합
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken()); // 수
			sumArr[i+1] = sumArr[i] + numbers[i]; // 누적
		}
		
		StringBuilder sb = new StringBuilder();
		int start, end, sum;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken()); // 시작
			end = Integer.parseInt(st.nextToken());   // 끝
			sum = sumArr[end] - sumArr[start-1];      // 합
			sb.append(sum+"\n");
		}
		
		System.out.println(sb);

	}//main

}//class

/*

5 3
5 4 3 2 1
1 3
2 4
5 5
----------
12
9
1

*/