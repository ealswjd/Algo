import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2565
public class Main {
	static final int A=0, B=1;
	static int cnt;
	static int[][] num;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cnt = Integer.parseInt(br.readLine()); // 두 전봇대 사이의 전깃줄의 개수
		num = new int[cnt+1][2]; // 전봇대 연결 정보
		dp = new int[cnt+1]; // 가장 많이 설치할 수 있는 전깃줄 개수
		
		StringTokenizer st;
		int a, b;
		for(int i=1; i<=cnt; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			num[i][A] = a;
			num[i][B] = b;
		}//for
		br.close();
		
		int max = getMax();
		System.out.print(cnt - max);
	}//main

	private static int getMax() {
		Arrays.sort(num, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[A] - o2[A]; // a 기준
			}
		});
		
		int max = 1;
		for(int i=1; i<=cnt; i++) {
			dp[i] = 1;
			for(int j=1; j<i; j++) {
				if(num[j][B] < num[i][B]) dp[i] = Math.max(dp[i], dp[j]+1);
			}//for j
			max = Math.max(max, dp[i]);
		}//for i
		
		return max;
	}//getMax

}//class