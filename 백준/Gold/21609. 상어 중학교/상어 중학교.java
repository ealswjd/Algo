import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/21609
public class Main {
	static int N, M, sum;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static PriorityQueue<Group> gpq; // 그룹
	static ArrayList<ArrayList<int[]>> blockList; // 각 그룹 블록

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		br.close();
		
		sum = 0;
		while(getGroup()) {
			autoPlay();			
		}//while
		
		System.out.print(sum);
	}//main

	private static void autoPlay() {
		int idx = getMaxGroup();
		sum += Math.pow(blockList.get(idx).size(), 2); // 제곱
		
		removeBlock(idx); // 블록 제거
		gravity();  // 중력		
		rotation(); // 반시계 방향 회전
		gravity();	// 중력	

	}//autoPlay
	
	private static void rotation() {
		int[][] tmp = new int[N][N];
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				tmp[r][c] = map[c][N-1-r];
			}//for c
		}//for r
		
		copyMap(tmp);
	}//rotation

	private static void gravity() {

		for(int c=0; c<N; c++) {
			for(int r=N-1; r>=0; r--) {
				if(map[r][c]==-1 || map[r][c]==-4) continue;
				int tr = r+1;
				while(tr<N) {
					if(map[tr][c] == -1 || map[tr][c] >= 0) break;
					tr++;
				}
				
				map[tr-1][c] = map[r][c]; 
				if(tr-1!=r)map[r][c] = -4; 
			}//for r
		}//for c

//		copyMap(tmp);
	}//gravity


	private static void copyMap(int[][] tmp) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = tmp[i][j];
			}//for j
		}//for i
		
	}//copyMap

	private static void removeBlock(int idx) {		
		for(int[] cur : blockList.get(idx)) {
			map[cur[0]][cur[1]] = -4;
		}//for		
	}//removeBlock

	private static int getMaxGroup() {
		gpq = new PriorityQueue<>();
		// 기준 블록
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] != o2[0]) return o1[0] - o2[0]; // 행의 번호가 가장 작은 블록
				return o1[1] - o2[1]; // 열의 번호가 가장 작은 블록
			}
		});
		
		int rainbowCnt;
		for(int i=0, size=blockList.size(); i<size; i++) {
			ArrayList<int[]> list = blockList.get(i);
			pq.clear();
			rainbowCnt = 0;
			for(int[] rc : list) {
				if(map[rc[0]][rc[1]] == 0) { // 기준 블록은 무지개 블록이 아닌 블록
					rainbowCnt++;
					continue; 
				}
				pq.offer(new int[] {rc[0], rc[1]});
			}//for rc
			
			gpq.offer(new Group(i, list.size(), rainbowCnt, pq.peek()[0], pq.poll()[1]));			
		}//for i
				
		return gpq.peek().idx;
	}//getMaxGroup

	private static boolean getGroup() {
		blockList = new ArrayList<>();
		
		boolean[][] visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] <= 0 || visited[i][j]) continue;
				visited[i][j] = true;
				bfs(i, j);
			}//for j
		}//for

		return blockList.size() > 0;
	}//getGroup

	private static void bfs(int r, int c) {
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();
		ArrayList<int[]> list = new ArrayList<>();
		q.offer(new int[] {r, c});
		list.add(new int[] {r, c});
		visited[r][c] = true;
		
		int nr, nc;
		int color = map[r][c];
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc) || map[nr][nc] == -4) continue;
				if((map[nr][nc] != 0 && map[nr][nc] != color) || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc});
				list.add(new int[] {nr, nc});
			}//for
		}//while
		
		if(list.size() > 1) blockList.add(list);
	}//bfs

	private static boolean rangeCheck(int r, int c) {
		return r<0 || r>=N || c<0 || c>=N || map[r][c]==-1;
	}//rangeCheck

	static class Group implements Comparable<Group>{
		int idx;
		int bCnt;
		int rainbowCnt;
		int rNum;
		int cNum;
		public Group(int idx, int bCnt, int rainbowCnt, int rNum, int cNum) {
			this.idx = idx;
			this.bCnt = bCnt;
			this.rainbowCnt = rainbowCnt;
			this.rNum = rNum;
			this.cNum = cNum;
		}
		@Override
		public int compareTo(Group o) {
			if(this.bCnt != o.bCnt) return o.bCnt - this.bCnt;
			else if(this.rainbowCnt != o.rainbowCnt) return o.rainbowCnt - this.rainbowCnt;
			else if(this.rNum != o.rNum) return o.rNum - this.rNum;
			return o.cNum - this.cNum;
		}//compareTo
		
	}//Group

}//class
