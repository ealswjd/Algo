import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13913
public class Main {
	static int N, K, size;
	static boolean[] visited;
	static int[] prev;
	static Stack<Integer> order;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
		K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치
		br.close();
		size = Math.max(N, K)*2+2;
		visited = new boolean[size];
		prev = new int[size];
		
		visited[N] = true;
		order = new Stack<>();
		int cnt = find();
		print(cnt);
	}//main

	private static void print(int cnt) {
		StringBuilder ans = new StringBuilder();
		ans.append(cnt).append("\n");
		
		while(!order.isEmpty()) {
			ans.append(order.pop()).append(" ");
		}//while
		System.out.print(ans);
	}//print

	private static int find() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {N, 0});
		
		int[] info;
		int cur, cnt=0, next;
		while(!q.isEmpty()) {
			info = q.poll();
			cur = info[0];
			cnt = info[1];

			if(cur == K) {
				int o = K;
				for(int i=0; i<=cnt; i++) {
					order.add(o);
					o = prev[o];
				}//for
				return cnt;
			}//if
			
			next = cur*2;
			if(rangeCheck(next) && !visited[next]) {
				q.offer(new int[] {next, cnt+1});
				prev[next] = cur;
				visited[next] = true;
			}
			
			next = cur+1;
			if(rangeCheck(next) && !visited[next]) {
				q.offer(new int[] {next, cnt+1});
				prev[next] = cur;
				visited[next] = true;
			}
			
			next = cur-1;
			if(rangeCheck(next) && !visited[next]) {
				q.offer(new int[] {next, cnt+1});
				prev[next] = cur;
				visited[next] = true;
			}
		}//while
		
		return cnt;
	}//find

	private static boolean rangeCheck(int n) {
		return n >= 0 && n < size;
	}//find
	

}//class