import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3197
public class Main {
	static int R, C;
	static int[][] map, visited;
	static char[][] origin;
	static int[] swan;
	static int sIdx, gCnt;
	static Queue<int[]> q;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int i=0; i<R; i++) {
			origin[i] = br.readLine().toCharArray();
		}//for		
		
		boolean[][] checked = new boolean[R][C];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(checked[i][j] || origin[i][j] == 'X') continue;
				makeGroup(i, j, ++gCnt, checked);
			}//for j
		}//for i
		
		parent = new int[gCnt+1];
		for(int i=1; i<=gCnt; i++) {
			parent[i] = i;
		}//for
		
		int day = melt();
		
		System.out.print(day);
	}//main

	private static int melt() {
		int r, c, nr, nc, group, day=0, prevDay=0;
		if(find(swan[0]) == find(swan[1])) return day;
		
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			day = cur[2];
			group = map[r][c];		
			
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc)) continue;
				
				if(origin[nr][nc] == '.') {
					if(map[nr][nc] > 0) union(map[nr][nc], group);
				}else {
					if(visited[nr][nc] != 0 && visited[nr][nc] <= visited[r][c]+1) continue;
					map[nr][nc] = group;
					origin[nr][nc] = '.';
					visited[nr][nc] = visited[r][c]+1;
					q.offer(new int[] {nr, nc, visited[nr][nc]});
					for(int j=0; j<4; j++) {
						int nnr = nr + dr[j];
						int nnc = nc + dc[j];
						if(rangeCheck(nnr, nnc)) continue;
						if(map[nnr][nnc] > 0) union(map[nnr][nnc], group);
						if(find(swan[0]) == find(swan[1])) return day;						
					}
				}
			}//for
			
			
		}//while
		
		return day;
	}//melt

	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false;
		
		if(a < b) parent[b] = a;
		else parent[a] = b;	
		return true;
	}//union

	private static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}//find

	private static void makeGroup(int r, int c, int g, boolean[][] checked) {
		Queue<int[]> gq = new LinkedList<>();
		gq.offer(new int[] {r, c});
		checked[r][c] = true;
		
		int[] cur;
		int nr, nc;
		while(!gq.isEmpty()) {
			cur = gq.poll();
			r = cur[0];
			c = cur[1];
			map[r][c] = g;
			if(origin[r][c] == 'L') {
				swan[sIdx++] = g;
				origin[r][c] = '.';
			}
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc) || checked[nr][nc]) continue;
				if(origin[nr][nc] == 'X') {
					if(visited[r][c] > 0) continue;
					q.offer(new int[] {r, c, 1});
					visited[r][c] = 1;
				} else gq.offer(new int[] {nr, nc});

				checked[nr][nc] = true;
			}//for
			
		}//while
		
	}//makeGroup

	// 범위 체크
	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}//rangeCheck

	// 초기화
	private static void init() {
		origin = new char[R][C];
		map = new int[R][C];
		visited = new int[R][C];
		q = new LinkedList<int[]>();
		swan = new int[2];	
		sIdx = gCnt = 0;
	}//init

}//class