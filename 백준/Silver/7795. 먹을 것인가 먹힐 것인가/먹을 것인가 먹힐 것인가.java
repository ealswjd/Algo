import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7795
public class Main {
	static int N, M; // A의 수 N과 B의 수 M
	static int[] A, B;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // A의 수
			M = Integer.parseInt(st.nextToken()); // B의 수
			A = new int[N];
			B = new int[M];
			
			init(A, new StringTokenizer(br.readLine()));
			init(B, new StringTokenizer(br.readLine()));
			
			getCnt(ans);
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

	private static void getCnt(StringBuilder ans) {
		int sum = 0, cnt = 0;
		int start = 0;
		int end = M-1;
		int mid = (start+end) / 2;
		
		for(int a : A) {
			if(a <= B[0]) continue;
			start = 0;
			end = M-1;
			cnt = 0;
			while(start <= end) {
				mid = (start+end) / 2;
				if(a > B[mid]) {
					start = mid+1;
					cnt = start;
				}else end = mid-1;
			}//while
			
			sum += cnt;
		}//A
		
		ans.append(sum).append("\n");
	}//getCnt

	private static void init(int[] arr, StringTokenizer st) {		
		for(int i=0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//for		
		Arrays.sort(arr);
	}//init

}//class