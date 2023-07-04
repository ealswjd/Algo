import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14502
public class Main {
	static int N, M, bCnt, area, max;
	static int[][] map;
	static boolean[][] visited;
	static Map<Integer, RC> blank;
	static ArrayList<RC> virus;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static Queue<int[]> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
		M = Integer.parseInt(st.nextToken()); // 가로 크기
		
		blank = new HashMap<>(); // 빈 칸 (벽 후보)
		virus = new ArrayList<>(); // 바이러스
		map = new int[N][M]; // 지도
		bCnt = area = max = 0; // 빈 칸 개수, 안전 영역, 최대 안전 영역
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 1) continue; // 벽
				else if(map[r][c] == 0) { // 빈 칸
					blank.put(bCnt++, new RC(r, c));
					area++; // 안전 영역
				}
				else virus.add(new RC(r, c)); // 바이러스 
			}//for c
		}//for r
		br.close();
		
		area -= 3; // 안전 영역 - 세워질 벽 3개
		q = new LinkedList<>();
		comb(new boolean[bCnt], 0, 0); // 벽 3개 세워보기
		
		System.out.print(max); // 안전 영역의 최대 크기 출력
	}//main
	
	private static void comb(boolean[] v, int start, int cnt) {
		if(cnt == 3) { // 벽 3개 다 세웠으면
			int virusCnt = area - bfs(); // 안전 영역 구해
			max = Math.max(max, virusCnt); // 최대 안전 영역 크기 비교
			return;
		}//if
		
		for(int i=start; i<bCnt; i++) {
			if(v[i]) continue;
			v[i] = true;
			map[blank.get(i).r][blank.get(i).c] = 1; // 벽 세워
			comb(v, i+1, cnt+1);
			v[i] = false;
			map[blank.get(i).r][blank.get(i).c] = 0; // 벽 다시 없애
		}//for
		
	}//comb

	private static int bfs() {
		int cnt = 0; // 바이러스 개수
		init(); // 초기화
		
		int r, c, nr, nc;
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(check(nr, nc)) continue; // 바이러스 퍼질 수 있는지 체크
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				cnt++; // 바이러스 개수 증가
			}//for
		}//while
		
		return cnt;
	}//bfs

	// 범위, 방문 여부 체크
	private static boolean check(int r, int c) {
		return r<0 || r>=N || c<0 || c>=M || visited[r][c] || map[r][c] != 0;
	}//check

	// 초기화
	private static void init() {
		RC v; // 바이러스 위치
		visited = new boolean[N][M]; // 방문 체크 배열 초기화
		for(int i=0, size=virus.size(); i<size; i++) {
			v = virus.get(i);
			q.offer(new int[] {v.r, v.c}); // 바이러스 담고
			visited[v.r][v.c] = true; // 방문 체크
		}//for		
	}//init

	static class RC {
		int r; // 행
		int c; // 열
		public RC(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}//RC

}//class