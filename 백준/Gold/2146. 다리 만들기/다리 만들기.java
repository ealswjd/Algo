import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2146
public class Main {
	static int N, min; // 지도 크기, 가장 짧은 다리
	static int[][] map; // 지도
	static int[][] tmpMap; // 지도
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static ArrayList<int[]> list; // 다리를 지을 수 있는 후보들

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N]; // 전체 지도
		tmpMap = new int[N][N]; // 섬 표시 지도
		min = 100000; // 가장 짧은 다리의 길이
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				tmpMap[i][j] = -1;
			}//for j
		}//for i
		br.close();

		int num=0;
		list = new ArrayList<>();
		boolean[][] v = new boolean[N][N]; // 방문 체크
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c]==0 || v[r][c]) continue;
				tmpMap[r][c] = num;
				v[r][c] = true;
				islandSearch(r, c, num++, v); // 섬 나누기
			}//for c
		}//for r
		
		// 후보 탐색
		for(int i=0, size=list.size(); i<size; i++) {
			min = Math.min(min, makeBridge(list.get(i)) );
		}//for 
		
		System.out.print(min); // 가장 짧은 다리의 길이

	}//main
	
	private static int makeBridge(int[] cur) {
		Queue<int[]> bridge = new LinkedList<>();
		int r = cur[0];
		int c = cur[1];
		boolean[][] visited = new boolean[N][N];
		visited[r][c] = true;
		bridge.offer(cur);
		
		int nr, nc, num, cnt;
		while(!bridge.isEmpty()) {
			cur = bridge.poll();
			r = cur[0];   // 행
			c = cur[1];   // 열
			num = cur[2]; // 섬 번호
			cnt = cur[3]; // 다리 길이			
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc) || visited[nr][nc] || tmpMap[nr][nc]==num) continue;
				// 다음 위치가 다른 섬이라면
				if(tmpMap[nr][nc]!=-1 && tmpMap[nr][nc] != num) {
					return cnt-1;
				}//if
				// 다음 탐색 
				bridge.offer(new int[] {nr, nc, num, cnt+1});
				visited[nr][nc] = true;
			}//for			
		}//while
		return 1111;
	}//makeBridge

	// 범위 체크
	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=N || c<0 || c>=N;
	}//rangeChekc

	// 섬 탐색
	private static void islandSearch(int r, int c, int num, boolean[][] visited) {
		boolean[][] checked = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		checked[r][c] = true;
		
		int[] cur;
		int nr, nc;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			boolean flag = false;
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
				if(map[nr][nc]==0) { // 현재 섬이 가장자리 섬인지 체크
					flag = true;
					continue;
				}
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				tmpMap[nr][nc] = num;
			}//for
			if(flag) { // 현재 섬이 가장자리면 다리 탐색을 위해 큐에 담아줌
				list.add(new int[] {r, c, num, 1}); // 행, 열, 섬 번호, 다리 길이
			}
		}//while
		
	}//islandSearch

}//class
