import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17141
public class Main {
	static final int EMPTY=0, WALL=1, VIRUS=2, INF=987654321;;
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	static int N, M, min, emptyCnt;
	static int[][] map;
	static List<int[]> virusList;
	static Queue<int[]> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 연구소의 크기
		M = Integer.parseInt(st.nextToken()); // 바이러스의 개수
		map = new int[N][N];
		virusList = new ArrayList<>();
		
		emptyCnt = 0;
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				
				if(map[r][c] == EMPTY) emptyCnt++;
				else if(map[r][c] == VIRUS) { // 바이러스를 놓을 수 있는 칸이면
					virusList.add(new int[] {r, c}); // 바이러스 리스트에 추가
					map[r][c] = EMPTY; // 빈 칸으로 변경
					emptyCnt++;
				}
				
			}//for c
		}//for r
		br.close();
		
		q = new LinkedList<>();
		min = INF;
		int[] position = new int[M];
		pick(0, 0, position);
		
		if(min == INF) System.out.print(-1);
		else System.out.print(min);
	}//main

	private static void pick(int start, int cnt, int[] position) {
		if(cnt == M) {
			int time = bfs(position);
			min = Math.min(min, time);
			return;
		}//if
		
		for(int i=start, size=virusList.size(); i<size; i++) {
			position[cnt] = i;
			pick(i+1, cnt+1, position);
		}//for
		
	}//pick

	private static int bfs(int[] position) {
		boolean[][] visited = new boolean[N][N];
		int cnt = 0, time = 0, r, c;
		for(int i=0; i<M; i++) {
			r = virusList.get(position[i])[0];
			c = virusList.get(position[i])[1];
			q.offer(new int[] {r, c, 0});
			visited[r][c] = true;
		}//for
		
		int[] cur;
		
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			time = cur[2];
			cnt++;
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc, time+1});
			}//for
		}//while
		
		if(cnt == emptyCnt) return time;
		return INF;
	}//bfs

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=N || c<0 || c>=N || map[r][c] == WALL;
	}//rangeCheck

}//class