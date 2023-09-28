import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12851
public class Main {
	static int N, K, size, min;
	static int[] visited;
	static Map<Integer, Integer> cntMap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
		K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치
		br.close();
		
		init();
		
		find();
		print();
	}//main

	private static void print() {
		StringBuilder ans = new StringBuilder();
		ans.append(min).append("\n");
		ans.append(cntMap.get(min));
		
		System.out.print(ans);
	}//print

	private static void find() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {N, 0});
		
		int[] info;
		int cur, cnt=0;
		while(!q.isEmpty()) {
			info = q.poll();
			cur = info[0];
			cnt = info[1];
			
			if(cur == K && cnt <= min) {
				cntMap.put(cnt, cntMap.getOrDefault(cnt, 0) + 1);
				min = cnt;
				continue;
			}//if
			
			if(cnt+1 > min) continue;
			
			add(q, cur*2, cur, cnt+1);
			add(q, cur+1, cur, cnt+1);
			add(q, cur-1, cur, cnt+1);
		}//while

	}//find

	private static void add(Queue<int[]> q, int next, int cur, int cnt) {
		if(rangeCheck(next) && visitCheck(next, cnt)) {
			q.offer(new int[] {next, cnt});
			visited[next] = cnt;
		}//if		
	}//add

	private static boolean visitCheck(int next, int cnt) {
		return visited[next] == 0 || (visited[next] > 0 && visited[next] >= cnt);
	}//visitCheck

	private static boolean rangeCheck(int n) {
		return n >= 0 && n < size;
	}//rangeCheck
	
	private static void init() {
		size = Math.max(N, K)*2+2;	
		visited = new int[size];
		visited[N] = 1;		
		cntMap = new HashMap<>();
		min = Integer.MAX_VALUE;
	}//init

}//class