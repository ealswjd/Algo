import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16928
public class Main {
	static final int SIZE = 100;
	static int[] map;
	static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[SIZE+1];
		map = new int[SIZE+1];
		for(int i=1; i<=SIZE; i++) map[i] = i;
		
		int x, y;
		// 사다리 정보
		while(N-->0) {
			st = new StringTokenizer(br.readLine()); 
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			map[x] = y;
		}//while
		
		// 뱀 정보
		while(M-->0) {
			st = new StringTokenizer(br.readLine()); 
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			map[x] = y;
		}//while
		br.close();
		
		int cnt = bfs();
		System.out.print(cnt);
	}//main

	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {1, 0});
		visited[1] = true;
		
		int[] cur;
		int n, cnt, next;
		while(!q.isEmpty()) {
			cur = q.poll();
			n = cur[0];
			cnt = cur[1];
			if(n == SIZE) return cnt;
			
			for(int i=1; i<=6; i++) {
				next = n+i;
				if(next > SIZE || visited[next]) continue;				
				q.offer(new int[] {map[next], cnt+1});
				visited[next] = true;
			}//for
		}//while
		
		return 0;
	}//bfs

}//class