import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3067
public class Main {
	static int N;
	static PriorityQueue<Integer> coins;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int M;
		while(T-->0) {
			N = Integer.parseInt(br.readLine()); // 동전의 가지 수
			coins = new PriorityQueue<>(); // N 가지 동전
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				coins.add(Integer.parseInt(st.nextToken()));
			}//for
			
			M = Integer.parseInt(br.readLine()); // 만들어야 할 금액
			
			ans.append(getCnt(M)).append('\n');			
		}//while

		System.out.print(ans);
	}//main

	private static int getCnt(int M) {
		dp = new int[M+1];
		dp[0] = 1;
		
		int coin;
		while(!coins.isEmpty()) {
			coin = coins.poll();
			for(int i=coin; i<=M; i++) {
				dp[i] += dp[i-coin];
			}//for
		}//while
		
		return dp[M];
	}//getCnt

}//class