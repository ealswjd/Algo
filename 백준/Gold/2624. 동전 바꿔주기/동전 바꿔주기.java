import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2624
public class Main {
	static final int P=0, CNT=1;
	static int T, K;
	static int[][] coins;
	static long[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); // 지폐의 금액
		K = Integer.parseInt(br.readLine()); // 동전의 가지 수
		coins = new int[K][2];
		dp = new long[T+1];
		
		StringTokenizer st;
		int p, cnt;
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken()); // 동전의 금액 
			cnt = Integer.parseInt(st.nextToken()); // 개수
			coins[i][P] = p;
			coins[i][CNT] = cnt;
		}//for
		br.close();
		
		System.out.print(getCnt());
	}//main

	private static long getCnt() {
		dp[0] = 1;
		
		int n, coin;
		for(int i=0; i<K; i++) {
			coin = coins[i][P];
			n = coins[i][CNT];
			for(int j=T; j>=coin; j--) {
				for(int cnt=1; cnt<=n; cnt++) {
					if(j - coin * cnt < 0) break;
					dp[j] += dp[j - coin * cnt];
				}//cnt
			}//j
		}//i
		
		return dp[T];
	}//getCnt

}//class