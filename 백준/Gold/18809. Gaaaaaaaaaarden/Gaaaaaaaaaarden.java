import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18809
public class Main {
	static int N, M, G, R, K;
	static int[][] garden; // 정원
	static Map<Integer, Node> map; // 배양액을 뿌릴 수 있는 땅
	static int[] random, keys;	
	static int max;
	static Queue<int[]> q;
	static final int GREEN = 0, RED = 1;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행의 개수
		M = Integer.parseInt(st.nextToken()); // 열의 개수
		G = Integer.parseInt(st.nextToken()); // 초록색 배양액의 개수
		R = Integer.parseInt(st.nextToken()); // 빨간색 배양액의 개수
		K = G + R;
		
		garden = new int[N][M];
		map = new HashMap<>();
		for(int i=0, key=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				garden[i][j] = Integer.parseInt(st.nextToken()) - 1;
				if(garden[i][j]==1) map.put(key++, new Node(i, j));
			}//for j
		}//for i
		br.close();
			
		random = new int[K];
		keys = new int[map.size()];
		for(int i=0; i<map.size(); i++) {
			keys[i] = i; 
		}//for
		
		max = 0;
		boolean[] visited = new boolean[map.size()];
		q = new LinkedList<>();
		comb(visited, 0, 0);
		
		System.out.print(max);
	}//main
	
	private static void comb(boolean[] visited, int start, int cnt) {
		if(cnt == K) {
			boolean[] v = new boolean[K];
			culture(v, 0, 0);
			return;
		}//if
		
		for(int i=start; i<map.size(); i++) {
			if(visited[i]) continue;
			visited[i] = true;
			random[cnt] = i;
			comb(visited, i+1, cnt+1);
			visited[i] = false;
		}//for
		
	}//comb

	private static void culture(boolean[] v, int start, int cnt) {
		if(cnt == G) {
			Node node;
			int[][][] visited = new int[2][N][M];
			int color;
			for(int i=0; i<K; i++) {
				node = map.get(random[i]);
				if(v[i]) color = GREEN;
				else color = RED;
				visited[color][node.r][node.c] = 1;
				q.offer(new int[] {node.r, node.c, 1, color});
			}//for
			bfs(visited);
			return;
		}//if
		
		for(int i=start; i<K; i++) {
			if(v[i]) continue;
			v[i] = true;
			culture(v, i+1, cnt+1);
			v[i] = false;
		}//for
		
	}//culture

	private static void bfs(int[][][] visited) {		
		
		int[] cur;
		int r, c, t, color, color2, nr, nc, cnt=0;;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			t = cur[2];
			color = cur[3];
			color2 = color==GREEN ? RED : GREEN;
			if(visited[color][r][c] < 0 || visited[color2][r][c] < 0) continue;
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc, color, visited)) continue;
				if(visited[color2][nr][nc] > 0 && visited[color2][nr][nc] < t+1) continue;
				else if(visited[color2][nr][nc] == t+1) {
					cnt++;
					visited[color][nr][nc] = -1;
					visited[color2][nr][nc] = -1;
					continue;
				}
				visited[color][nr][nc] = t+1;
				q.offer(new int[] {nr, nc, t+1, color});
			}//for
		}//while

		max = Math.max(max, cnt);		
	}//bfs

	private static boolean rangeCheck(int r, int c, int color, int[][][] visited) {		
		return r<0 || r>=N || c<0 || c>=M 
				|| garden[r][c] == -1 || visited[color][r][c] > 0  || visited[color][r][c]==-1;
	}//rangeCheck

	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}		
	}//node

}//class