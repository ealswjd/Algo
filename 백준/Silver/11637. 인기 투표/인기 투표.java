import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11637
public class Main {
	static int N; // 후보자 수
	static int[] cnt; // 각 후보자가 받은 득표 수
	static final String WINNER = "majority winner "; 
	static final String MINO_WINNER = "minority winner "; 
	static final String NO_WINNER = "no winner"; 
	static StringBuilder ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		
		ans = new StringBuilder();
		int total, R, max;
		while(T-->0) {
			N = Integer.parseInt(br.readLine()); // 후보자 수
			cnt = new int[N]; // 각 후보자가 받은 득표 수
			total = 0;
			max = 0;
			R = 0;
			for(int i=0; i<N; i++) {
				cnt[i] = Integer.parseInt(br.readLine());
				total += cnt[i];
				max = Math.max(max, cnt[i]);
				if(max == cnt[i]) R = i+1;
			}//for
			
			getResult(total, max, R);
			ans.append("\n");
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

	// 투표 결과
	private static void getResult(int total, int max, int R) {
		int sum = 0;
		for(int n : cnt) {
			if(n == max) sum++;
			if(sum > 1) {
				ans.append(NO_WINNER);
				return;
			}
		}//for
		
		if(max * 2 > total) ans.append(WINNER).append(R);
		else ans.append(MINO_WINNER).append(R);
	}//getResult

}//class
