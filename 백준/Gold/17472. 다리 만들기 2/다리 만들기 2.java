import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17472
public class Main {
	static final int SEA = 0;
	static int N, M, iCnt;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static ArrayList<HashSet<int[]>> island;
	static ArrayList<HashSet<Node>> nodeList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		// 지도 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		br.close();
		
		island = new ArrayList<>(); // 섬 정보
		nodeList = new ArrayList<>(); // 섬을 연결하는 다리 정보
		// 섬 개수 구하기 & 섬 번호 바꾸기
		iCnt = getIslandCnt();
		for(int i=0; i<iCnt; i++) {
			nodeList.add(new HashSet<>());
		}//for
		
		bridgeConnection(); // 다리 연결
		int min = getMinLength(); // 모든 섬을 연결하는 다리 길이의 최솟값을 구해보자.
		
		System.out.print(min);
	}//main

	// 모든 섬을 연결하는 다리 길이의 최솟값을 구해보자.
	private static int getMinLength() {
		boolean[] visited = new boolean[iCnt];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0));
		
		Node cur;
		int to, len;
		int min = 0;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			to = cur.to;
			len = cur.length;
			if(visited[to]) continue;
			visited[to] = true;
			min += len;
			for(Node next : nodeList.get(to)) {
				if(!visited[next.to]) pq.offer(next);
			}//for
		}//while
		
		for(int i=0; i<iCnt; i++) {
			if(!visited[i]) return -1;
		}//for
		
		return min;
	}//getMinLength

	// 다리 연결
	private static void bridgeConnection() {
		HashSet<int[]> set;
		for(int i=0; i<iCnt; i++) {
			set = island.get(i);
			for(int[] rc : set) {
				for(int d=0; d<4; d++) {
					dfs(rc[0], rc[1], dr[d], dc[d], 0, map[rc[0]][rc[1]]);
				}//d
			}//rc
		}//iCnt
		
	}//bridgeConnection

	// 한 방향으로 다리 연결
	private static void dfs(int r, int c, int dr, int dc, int len, int num) {
		r += dr;
		c += dc;
		if(rangeCheck(r, c)) return;
		if(map[r][c] != num && map[r][c] != SEA) {
			if(len >= 2) nodeList.get(num-1).add(new Node(map[r][c]-1, len));
			return;
		}//if
		if(map[r][c] == 0) dfs(r, c, dr, dc, len+1, num);
	}//dfs

	// 섬 개수 구하기 & 섬 번호 바꾸기
	private static int getIslandCnt() {
		int cnt = 0;
		boolean[][] visited = new boolean[N][M];
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(visited[r][c] || map[r][c]==0) continue;
				visited[r][c] = true;
				map[r][c] = ++cnt;
				bfs(cnt, r, c, visited);
			}//c
		}//r
		
		return cnt;
	}//getIslandCnt

	private static void bfs(int num, int r, int c, boolean[][] visited) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		HashSet<int[]> set = new HashSet<>();
		
		int[] cur;
		int nr, nc;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
				if(map[nr][nc] == 0) {
					set.add(cur);
					continue;
				}
				visited[nr][nc] = true;
				map[nr][nc] = num;
				q.offer(new int[] {nr, nc});
			}
		}//while
		
		island.add(set);
	}//bfs
	
	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}//rangeCheck
	
	static class Node implements Comparable<Node> {
		int to;
		int length;
		public Node(int to, int length) {
			this.to = to;
			this.length = length;
		}
		@Override
		public int compareTo(Node n) {
			return this.length - n.length;
		}		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + length;
			result = prime * result + to;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (length != other.length)
				return false;
			if (to != other.to)
				return false;
			return true;
		}	
	}//Node

}//class