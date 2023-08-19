import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21921
public class Main {
	static int N, X;
	static int[] visitors;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		visitors = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			visitors[i] = Integer.parseInt(st.nextToken()) + visitors[i-1];
		}//for
		br.close();
		
		getVisitorCnt();
	}//main

	private static void getVisitorCnt() {
		Map<Integer, Integer> cntMap = new HashMap<Integer, Integer>();
		int max = 0; // 최대 방문자 수
		int start = 0;
		int end = X;
		int sum = 0;
		
		while(end <= N) {
			sum = visitors[end++] - visitors[start++];
			if(sum >= max) {
				max = sum;
				cntMap.put(sum, cntMap.getOrDefault(sum, 0) + 1);
			}//if
		}//while
		
		StringBuilder ans = new StringBuilder();
		if(max == 0) ans.append("SAD");
		else {
			ans.append(max).append("\n");
			ans.append(cntMap.get(max));
		}//else
		
		System.out.print(ans);
	}//getVisitorCnt

}//class