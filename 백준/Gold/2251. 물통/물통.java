import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2251
public class Main {
	static PriorityQueue<Integer> cList;
	static int N;
	static final int A=0, B=1, C=2;
	static int[] max;
	static int[] from = {A, A, B, B, C, C};
	static int[] to = {B, C, A, C, A, B};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = 3;
		max = new int[N];
		for(int i=0; i<N; i++) {
			max[i] = Integer.parseInt(st.nextToken());
		}//for
		br.close();
		
		cList = new PriorityQueue<>();
		bfs();
		
		StringBuilder ans = new StringBuilder();
		while(!cList.isEmpty()) {
			ans.append(cList.poll()).append(" ");
		}
		System.out.print(ans);
	}//main

	private static void bfs() {
		boolean[][] visited = new boolean[max[A]+1][max[B]+1];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0});
		visited[0][0] = true;
		
		int[] cur;
		int a, b, c;
		while(!q.isEmpty()) {
			cur = q.poll();
			a = cur[A];
			b = cur[B];
			c = max[C] - (a + b);
			
			if(a == 0) cList.add(c);

			for(int i=0; i<6; i++) {
				int[] next = {a, b, c};
				next[to[i]] += next[from[i]];
				next[from[i]] = 0;
				
				if(next[to[i]] > max[to[i]]) {
					next[from[i]] = next[to[i]] - max[to[i]];
					next[to[i]] = max[to[i]];
				}
				if(visited[next[A]][next[B]]) continue;
				
				visited[next[A]][next[B]] = true;
				q.offer(next);
			}//for
			
		}//while
		
	}//bfs


}//class