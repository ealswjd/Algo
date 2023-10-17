import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2623
public class Main {
	static int N; // 가수의 수
	static int[] edgeCnt; // 앞사람 수
	static ArrayList<ArrayList<Integer>> list;
	static StringBuilder ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 가수의 수
		int M = Integer.parseInt(st.nextToken()); // 보조 PD의 수
		
		init();
		
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken()); // 담당 가수의 수
			int prev = Integer.parseInt(st.nextToken()); // 이전 가수
			for(int i=1,cur=0; i<cnt; i++) {
				cur = Integer.parseInt(st.nextToken());
				list.get(prev).add(cur);
				edgeCnt[cur]++; // 이전 사람 수 증가
				prev = cur;
			}//for
		}//while
				
		if(getOrder()) System.out.print(ans);
		else System.out.print(0);

	}//main

	private static boolean getOrder() {
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(edgeCnt[i] == 0) q.offer(i); // 바로 출연 가능한 가수
		}//for
		
		int cnt = 0;
		int cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			if(cnt++ == N) return false; // 순서 정하는 것이 불가능
			ans.append(cur).append("\n");
			
			for(int next : list.get(cur)) {
				edgeCnt[next]--; // 앞 사람 제거
				if(edgeCnt[next] == 0) q.offer(next); // 출연 가능해짐
			}//for
		}//while
		
		return cnt==N; // 모든 가수 출연 완료. 출연 순서 정해짐
	}//getOrder

	private static void init() {
		ans = new StringBuilder();
		edgeCnt = new int[N+1];
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}//for		
	}//init

}//class