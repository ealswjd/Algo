import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 백조의 호수 (PLA 5)
 * 시간 : 1024 ms
 * 메모리: 186672 KB
 * 링크 : https://www.acmicpc.net/problem/3197
 * */
public class Main {
	static int R, C; // 행, 열
	static int[][] map, visited; // 그룹 나눈 지도, 방문 체크
	static char[][] origin; // 입력 받은 원래 지도
	static int[] swan; // 백조 그룹
	static int sIdx, gCnt; // 백조 인덱스, 그룹 개수
	static Queue<int[]> q; // 얼음 녹이는 큐
	static int[] parent; // 부모
	// 상 하 좌 우
	static int[] dr = {-1, 1, 0, 0}; 
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		
		init(); // 초기화
		
		// 지도 입력
		for(int i=0; i<R; i++) {
			origin[i] = br.readLine().toCharArray();
		}//for		
		
		boolean[][] checked = new boolean[R][C]; // 물 그룹 방문 체크
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				// 이미 방문 했거나 얼음이면 넘어가
				if(checked[i][j] || origin[i][j] == 'X') continue;
				makeGroup(i, j, ++gCnt, checked); // 그룹 만들러
			}//for j
		}//for i
		
		// 부모 자기 자신으로 초기화
		parent = new int[gCnt+1];
		for(int i=1; i<=gCnt; i++) {
			parent[i] = i;
		}//for
		
		int day = melt(); // 얼음 녹이기
		System.out.print(day);
	}//main

	// 얼음 녹이기
	private static int melt() {
		int r, c, nr, nc, group, day=0;
		// 이미 백조 만났으면 그만
		if(find(swan[0]) == find(swan[1])) return day;
		
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll(); // 현재 위치
			r = cur[0]; // 행
			c = cur[1]; // 열
			day = cur[2]; // 날짜
			group = map[r][c]; // 현재 그룹				
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i]; // 다음 행
				nc = c + dc[i]; // 다음 열
				// 범위 벗어나면 넘어가기
				if(rangeCheck(nr, nc)) continue;
				
				// 물 공간이고
				if(origin[nr][nc] == '.') {
					// 그룹이 있으면 현재 그룹과 다음 그룹 유니온
					if(map[nr][nc] > 0 && map[nr][nc] != group) union(map[nr][nc], group);
				}else {
					// 얼음 공간, 이미 방문 했다면 넘어가기
					if(visited[nr][nc] != 0 && visited[nr][nc] <= visited[r][c]+1) continue;
					map[nr][nc] = group; // 현재 그룹 표시 
					origin[nr][nc] = '.'; // 얼음 녹이고
					visited[nr][nc] = visited[r][c]+1; // 며칠에 방문 했는지 체크
					q.offer(new int[] {nr, nc, visited[nr][nc]});
					// 녹일 때마다 주변 탐색
					for(int j=0; j<4; j++) {
						int nnr = nr + dr[j];
						int nnc = nc + dc[j];
						if(rangeCheck(nnr, nnc)) continue;
						if(map[nnr][nnc] > 0 && map[nnr][nnc] != group) union(map[nnr][nnc], group);
						// 백조끼리 만났으면 그만
						if(find(swan[0]) == find(swan[1])) return day;				
					}//for
				}//else
			}//for
			
			
		}//while
		
		return day;
	}//melt

	// 유니온 (합치기)
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false;
		
		if(a < b) parent[b] = a;
		else parent[a] = b;	
		return true;
	}//union

	// 파인드 (부모 찾기)
	private static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}//find

	// 그룹 만들기
	private static void makeGroup(int r, int c, int g, boolean[][] checked) {
		Queue<int[]> gq = new LinkedList<>();
		gq.offer(new int[] {r, c});
		checked[r][c] = true;
		
		int[] cur;
		int nr, nc;
		while(!gq.isEmpty()) {
			cur = gq.poll(); // 현재 위치
			r = cur[0]; // 행
			c = cur[1]; // 열
			map[r][c] = g; // 그룹 표시
			// 백조일 경우
			if(origin[r][c] == 'L') { 
				swan[sIdx++] = g; // 백조 그룹 체크
				origin[r][c] = '.'; // 물 공간으로 변경
			}//if
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i]; // 다음 행
				nc = c + dc[i]; // 다음 열
				// 범위 벗어나거나 이미 방문 했으면 넘어가고
				if(rangeCheck(nr, nc) || checked[nr][nc]) continue;
				// 물과 닿아있는 얼음이면
				if(origin[nr][nc] == 'X') {
					if(visited[r][c] > 0) continue;
					q.offer(new int[] {r, c, 1}); // 큐에 현재 물 공간 넣어주기
					visited[r][c] = 1;
				} else gq.offer(new int[] {nr, nc}); // 물 공간이면 그룹 체크를 위해 담기

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