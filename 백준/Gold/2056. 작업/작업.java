import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2056
public class Main {
	static int N;
	static int[] edgeCnt, timeArr, dp;
	static ArrayList<ArrayList<Integer>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		init();
		
		StringTokenizer st;
		int time, cnt, n;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			time = Integer.parseInt(st.nextToken());
			cnt = Integer.parseInt(st.nextToken());
			while(cnt-->0) {
				n = Integer.parseInt(st.nextToken());
				edgeCnt[i]++;
				list.get(n).add(i);
			}//while
			timeArr[i] = time;
			dp[i] = time;
		}//for
		br.close();
		
		int ans = getTime();
		System.out.println(ans);
	}//main

	private static int getTime() {		
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(edgeCnt[i] == 0) q.offer(i);
		}//for		
		
		int cur;
		while(!q.isEmpty()) {
			cur = q.poll();			
			for(int next : list.get(cur)) {
				edgeCnt[next]--;
				dp[next] = Math.max(dp[next], dp[cur] + timeArr[next]);
				if(edgeCnt[next] == 0) q.offer(next);
			}//for			
		}//while
		
		int total = 0;
		for(int time : dp) {
			total = Math.max(time, total);
		}
		return total;
	}//getTime

	private static void init() {
		edgeCnt = new int[N+1];
		timeArr = new int[N+1];
		dp = new int[N+1];
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}//for
	}//init

}//class