import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16724
public class Main {
	static final int U=0, D=1, L=2, R=3; // 상하좌우
	static int N, M; // 행 열
	static int[][] map, group;
	static int[] parent;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 지도의 행
		M = Integer.parseInt(st.nextToken()); // 지도의 열
		init(); // 초기화		
		
		for(int i=0; i<N; i++) {
			char[] row = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				switch (row[j]) {
				case 'U': map[i][j] = U;					
					break;
				case 'D': map[i][j] = D;					
					break;
				case 'L': map[i][j] = L;					
					break;
				case 'R': map[i][j] = R;					
					break;
				}//switch
			}//for j
		}//for i
		br.close();
		
		// 방향, 다음행, 다음열, 현재, 다음, 그룹 번호
		int d, nr, nc, cur, next, g;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				cur = r * M + c; // 현재 위치
				d = map[r][c]; // 방향
				nr = r + dr[d]; nc = c + dc[d]; // 다음 행, 열
				next = nr * M + nc; // 다음 위치
				g = union(cur, next); // 그룹 번호
				group[r][c] = group[nr][nc] = g; // 그룹 번호 변경
			}//for j
		}//for i
		
		int cnt = getCnt(); // 그룹 개수 구하기		
		System.out.print(cnt);
	}//main

	private static int getCnt() {
		Set<Integer> groupNum = new HashSet<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				groupNum.add(find(group[i][j])); // 그룹 넣기
			}//for j
		}//for i
		
		return groupNum.size();
	}//getCnt

	// 그룹 합치기
	private static int union(int cur, int next) {
		cur = find(cur);
		next = find(next);
		
		if(cur == next) return cur;
		
		if(cur > next) parent[cur] = next;
		else parent[next] = cur;
		
		return parent[cur];
	}//union

	// 부모 찾기
	private static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}//find

	private static void init() {
		map = new int[N][M]; // 지도
		group = new int[N][M]; // 그룹 지도
		parent = new int[N*M]; // 부모
		for(int i=0, size=N*M; i<size; i++) {
			parent[i] = i;
		}//for		
	}//init


}//class