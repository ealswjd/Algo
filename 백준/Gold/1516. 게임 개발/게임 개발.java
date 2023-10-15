import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1516
public class Main {
	static int N; // 건물의 종류 수
	static int[] edgeCnt, time, result;
	static ArrayList<ArrayList<Integer>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 건물의 종류 수
		init(); // 초기화
		
		StringTokenizer st;
		for(int a=1; a<=N; a++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken()); // 건물을 짓는데 걸리는 시간
			time[a] = t; // 시간
			while(true) {
				int b = Integer.parseInt(st.nextToken());
				if(b == -1) break;
				list.get(b).add(a); // 먼저 지어야 되는 건물
				edgeCnt[a]++; // 먼저 지어야 되는 건물 개수 증가
			}//while
		}//for
		br.close();

		sort(); // 위상정렬
		print(); // 출력
	}//main

	private static void print() {
		StringBuilder ans = new StringBuilder();
		
		for(int i=1; i<=N; i++) { // 이전 건물 완성 시간 + 현재 건물 짓는 시간
			ans.append(result[i]+time[i]).append("\n"); 
		}//for
		
		System.out.print(ans);
	}//print

	private static void sort() {
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=1; i<=N; i++) {
			if(edgeCnt[i] == 0) q.offer(i); // 바로 지을 수 있는 건물
		}//for
		
		int cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int next : list.get(cur)) {
				edgeCnt[next]--; // 지어야 하는 건물 개수 감소
				if(edgeCnt[next] == 0) q.offer(next); // 이전 건물 다 지었으면 넣어주기
				// 이전 건물 완성 시간 갱신
				result[next] = Math.max(result[next], result[cur] + time[cur]); 
			}//for
		}//while
		
	}//sort

	private static void init() {
		edgeCnt = new int[N+1];
		time = new int[N+1];
		result = new int[N+1];
		
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}//for
		
	}//init

}//class