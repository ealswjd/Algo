import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3184
public class Main {
	static int R, C;
	static int sheepCnt, wolfCnt; // 양, 늑대 수
	static char[][] map; // 마당
	static boolean[][] visited;
	static final char S = 'o'; // 양
	static final char W = 'v'; // 늑대
	static final char X = '#'; // 울타리
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		
		sheepCnt = wolfCnt = 0;
		for(int i=0; i<R; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				map[i][j] = tmp[j];
				if(tmp[j] == S) sheepCnt++;
				else if(tmp[j] == W) wolfCnt++;
			}//for j
		}//for i
		br.close();
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(!visited[i][j] && map[i][j] == W ) {
					visited[i][j] = true;
					getCount(i, j);
				}//if
			}//for j
		}//for i
		
		StringBuilder sb = new StringBuilder();
		sb.append(sheepCnt+" "+wolfCnt);
		System.out.print(sb);

	}//main

	private static void getCount(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		int sheep=0, wolf=1, nr, nc;
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc)) continue;
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				if(map[nr][nc] == S) sheep++;
				else if(map[nr][nc] == W) wolf++;
			}//for
		}//while
		
		if(sheep > wolf) wolfCnt -= wolf;
		else sheepCnt -= sheep;
	}//getCount

	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C || visited[r][c] || map[r][c]==X;
	}//rangeCheck

}//class

/*

6 6
...#..
.##v#.
#v.#.#
#.o#.#
.###.#
...###

*/