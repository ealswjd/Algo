import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17071
public class Main {
	static int N, K, max;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
		K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치
		br.close();
		
		init();

		System.out.print(find());
	}//main

	private static int find() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {N, 0, K});
		
		int[] info;
		int cur, cnt=0, k=0;
		while(!q.isEmpty()) {
			info = q.poll();
			cur = info[0];
			cnt = info[1];
			k = info[2];
			
			if(k > max) return -1;
			if(visited[cnt%2][k]) return cnt;			
			
			add(q, cur*2, cur, cnt+1, k);
			add(q, cur+1, cur, cnt+1, k);
			add(q, cur-1, cur, cnt+1, k);
		}//while
		return cnt;
	}//find

	private static void add(Queue<int[]> q, int next, int cur, int cnt, int k) {
		if(rangeCheck(next) && visitedCheck(next, cnt%2)) {
			q.offer(new int[] {next, cnt, k+cnt});
			visited[cnt%2][next] = true;
		}//if		
	}//add

	private static boolean visitedCheck(int next, int idx) {
		return !visited[idx][next];
	}//visitedCheck

	private static boolean rangeCheck(int n) {
		return n >= 0 && n <= max;
	}//rangeCheck
	
	private static void init() {
		max = 500_000;
		visited = new boolean[2][max+1];
		visited[0][N] = true;	
	}//init

}//class