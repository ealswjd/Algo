import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2293
public class Main {
	static PriorityQueue<Integer> coins;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		coins = new PriorityQueue<>();
		while(N-->0) {
			coins.add(Integer.parseInt(br.readLine()));
		}//while
		br.close();
		
		getCnt(K);		
		System.out.print(dp[K]);
	}//main

	private static void getCnt(int K) {
		int coin;
		dp = new int[K+1];
		dp[0] = 1;
		
		while(!coins.isEmpty()) {
			coin = coins.poll();
			for(int i=coin; i<=K; i++) {
				dp[i] += dp[i-coin];
			}//for
		}//while
		
	}//getCnt

}//class