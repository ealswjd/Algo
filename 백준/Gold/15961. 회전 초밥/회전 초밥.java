import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15961
public class Main {
	// 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
	static int N, D, K, C;
	static int max, cnt;
	static int[] plates;
	static int[] types;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 접시의 수
		D = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		K = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		C = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		plates = new int[N];
		types = new int[D+1];
		for(int i=0; i<N; i++) {
			plates[i] = Integer.parseInt(br.readLine());
		}//for
		br.close();

		getMax();
		System.out.print(max);
	}//main

	private static void getMax() {
		max = 0;
		cnt = 0;
		for(int i=0; i<K; i++) {
			if(types[plates[i]] == 0) cnt++;
			types[plates[i]]++;			
		}//for
		
		if(types[C] > 0) max = cnt;
		else max = cnt + 1;
		
		int prev = 0, cur = K;
		rotation(prev, cur, N);
		
		prev = N-K; cur = 0;		
		rotation(prev, cur, K-1);

	}//getMax

	private static void rotation(int prev, int cur, int len) {
		
		while(cur < len) {
			types[plates[prev]]--;
			if(types[plates[prev++]] == 0) cnt--;
			if(types[plates[cur]] == 0) cnt++;
			types[plates[cur++]]++;
			if(cnt >= max) {
				if(types[C] > 0) max = cnt;
				else max = cnt+1;
			}
		}//while
		
	}//rotation

}//class