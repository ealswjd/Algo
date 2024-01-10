import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2252
public class Main {
	static int N, M;
	static int[] edgeCnt;
	static ArrayList<ArrayList<Integer>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 키를 비교한 회수
		
		edgeCnt = new int[N+1];
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}//for
		
		int a, b; // 키를 비교한 두 학생의 번호
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			// 학생 A가 학생 B의 앞에 서야 한다는 의미
			list.get(a).add(b);
			edgeCnt[b]++;
		}//while
		br.close();
		
		lineUp();

	}//main

	private static void lineUp() {
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(edgeCnt[i] == 0) q.add(i);
		}//for
		
		StringBuilder ans = new StringBuilder();
		int cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			ans.append(cur).append(' ');
			
			for(int next : list.get(cur)) {
				edgeCnt[next]--;
				if(edgeCnt[next] == 0) q.add(next);
			}//for
			
		}//while
		
		System.out.print(ans);
	}//lineUp

}//class