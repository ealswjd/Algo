import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9944
public class Main {
	static int N, M, min, empty; // 행, 열, 최소 이동 횟수, 빈칸 개수
	static int[][] map; // 보드
	static boolean[][] visited;
	static ArrayList<int[]> emptyList; // 빈칸 리스트
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		int caseNum = 1;
		
		String line = "";
		while((line=br.readLine()) != null && !line.isEmpty()) {
			st = new StringTokenizer(line);
			N = Integer.parseInt(st.nextToken()); // 행
			M = Integer.parseInt(st.nextToken()); // 열
			map = new int[N][M]; // 보드
			empty = 0; // 빈칸 개수
			emptyList = new ArrayList<>();
			for(int i=0; i<N; i++) {
				char[] tmp = br.readLine().toCharArray();
				for(int j=0; j<M; j++) {
					if(tmp[j]=='*') map[i][j] = 1;
					else {
						empty++; // 빈칸 개수 증가
						emptyList.add(new int[] {i, j}); // 빈칸 리스트 담기
					}//else
				}//for
			}//for
			
			min = Integer.MAX_VALUE;
			visited = new boolean[N][M]; // 방문체크
			for(int i=0; i<empty; i++) {
				int r = emptyList.get(i)[0];
				int c = emptyList.get(i)[1];
				visited[r][c] = true;
				move(r, c, empty-1, 0);
				visited[r][c] = false;				
			}
			
			if(min == Integer.MAX_VALUE) min = -1;
			ans.append("Case ").append(caseNum++).append(": ").append(min);
			ans.append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static void move(int r, int c, int emptyCnt, int moveCnt) {
		if(moveCnt > min) return;
		if(emptyCnt == 0) {
			min = Math.min(min, moveCnt);
			return;
		}//if

		int cnt;
		for(int i=0; i<4; i++) {
			int nr = r;
			int nc = c;
			cnt = 0;
			while(!rangeCheck(nr+dr[i], nc+dc[i]) && !visited[nr+dr[i]][nc+dc[i]] && map[nr+dr[i]][nc+dc[i]]==0) {
				nr += dr[i];
				nc += dc[i];
				cnt++;
				visited[nr][nc] = true;
			}//while
			
			if(cnt > 0) {
				move(nr, nc, emptyCnt-cnt, moveCnt+1);
				while(!(nr==r && nc==c)) {
					visited[nr][nc] = false;
					nr -= dr[i];
					nc -= dc[i];
				}//while
			}//if
			
		}//for
	
	}//move

	// 범위 체크
	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}//rangeCheck

}//class