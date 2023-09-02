import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16932
public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> zero;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static HashMap<Integer, Integer> group;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		zero = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) zero.add(new int[] {i, j});
			}//for j
		}//for i
		br.close();
		
		visited = new boolean[N][M];
		group = new HashMap<>();
		int g = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					visited[i][j] = true;
					map[i][j] = g;
					int cnt = makeGroup(i, j, g, 1);
					group.put(g++, cnt);
				}
			}
		}//for i

		int max = getMax();
		System.out.print(max);
	}//main

	private static int makeGroup(int r, int c, int g, int cnt) {
		visited[r][c] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(rangeCheck(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) continue;
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc});
				map[nr][nc] = g;
				cnt++;
			}//for
		}//while		
		
		return cnt;
	}//makeGroup

	private static int getMax() {
		int max = 0, cnt = 0;
		int r, c;
		HashSet<Integer> groupVisited;
		int[] cur;
		
		while(!zero.isEmpty()) {
			cur = zero.poll();
			r = cur[0];
			c = cur[1];
			cnt = 1;
			groupVisited = new HashSet<>();
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(rangeCheck(nr, nc) || map[nr][nc] == 0 || groupVisited.contains(map[nr][nc])) continue;
				cnt += group.get(map[nr][nc]);
				groupVisited.add(map[nr][nc]);
			}
			max = Math.max(max, cnt);
		}//while
		
		return max;
	}//getMax


	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}//rangeCheck

}//class