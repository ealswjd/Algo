import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/1005
public class Main {
	static final int INF=100_000_001;
	static int N, K, W;
	static int[] cnts, times, min, max;
	static List<ArrayList<Integer>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		while(T-->0) {
			// 첫째 줄에 건물의 개수 N과 건물간의 건설순서 규칙의 총 개수 K이 주어진다.
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 건물의 개수
			K = Integer.parseInt(st.nextToken()); // 규칙 개수
			
			init();		
			
			// 둘째 줄에는 각 건물당 건설에 걸리는 시간이 공백을 사이로 주어진다. 
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				times[i] = Integer.parseInt(st.nextToken());
			}//for
			
			// 셋째 줄부터 K+2줄까지 건설순서 X Y가 주어진다.
			// 건물 X를 지은 다음에 건물 Y를 짓는 것이 가능하다는 의미
			int x, y;
			while(K-->0) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken()) - 1; 
				y = Integer.parseInt(st.nextToken()) - 1; 
				cnts[y]++;
				list.get(x).add(y);
			}//while
			
			// 마지막 줄에는 백준이가 승리하기 위해 건설해야 할 건물의 번호 W가 주어진다.
			W = Integer.parseInt(br.readLine()) - 1; // 건설해야 할 건물의 번호
			
			ans.append(getTime()).append('\n');
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static int getTime() {
		Queue<Integer> q = new LinkedList<>();	
		for(int i=0; i<N; i++) {
			if(cnts[i] == 0) {
				q.offer(i);
				min[i] = times[i];
			}
		}//for

		int cur, order, time=0;
		ArrayList<Integer> tmp;
		while(!q.isEmpty()) {
			cur = q.poll();			
			if(cur == W) return min[W];
			
			tmp = list.get(cur);
			for(int next : tmp) {
				cnts[next]--;
				min[next] = Math.max(min[cur] + times[next], min[next]);
				if(cnts[next] == 0) q.offer(next);
			}
		}//while
		
		return min[W];
	}//getTime

	private static void init() {
		cnts = new int[N]; // 먼저 지어야 되는 건물 개수
		times = new int[N]; // 건물당 건설에 걸리는 시간
		min = new int[N]; // 최소 시간
		list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());
		}//for
	}//init

}//class