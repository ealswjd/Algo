import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// hhttps://www.acmicpc.net/problem/6603
public class Main {
	static int K;
	static int[] number, lotto;	
	static StringBuilder ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		ans = new StringBuilder();
		lotto = new int[6];
		while(true) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			if(K == 0) break;
			
			number = new int[K];
			for(int i=0; i<K; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}//for
			
			getLotto(0, 0);
			ans.append("\n");
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

	private static void getLotto(int start, int cnt) {
		if(cnt == 6) {
			for(int i=0; i<6; i++) {
				ans.append(lotto[i]).append(" ");
			}//for
			ans.append("\n");
			return;
		}//if
		
		for(int i=start; i<K; i++) {
			lotto[cnt] = number[i];
			getLotto(i+1, cnt+1);
		}//for
		
	}//getLotto

}//class