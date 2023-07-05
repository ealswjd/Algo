import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/1938
public class Main {
	static int N, min; // 지도 크기, 최소 동작 횟수 
	static char[][] map; // 지도
	static int[][][] visited; // 방문 체크
	static int[][][] position; // 통나무 위치, 목적지 위치
	static final int B = 0, E = 1; // 통나무, 목적지
	static final int W = 0, H = 1; // 가로, 세로
	static int ED = W; // 목적지 방향
	static int[] dr = {-1, 1, 0, 0}; // 상하
	static int[] dc = {0, 0, -1, 1}; // 좌우

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new int[2][N][N];
		position = new int[2][3][2];
		
		for(int i=0, b=0, e=0; i<N; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<N; j++) {
				map[i][j] = tmp[j];
				if(tmp[j] == 'E') position[E][e++] = new int[] {i, j};
				else if(tmp[j] == 'B') position[B][b++] = new int[] {i, j};
			}//for j
		}//for i
		br.close();
		
		Queue<int[]> q = new LinkedList<>();
		min = 987654321;
		directionCheck(q); // 통나무, 목적지 가로 세로 체크	
		move(q); // 통나무 옮기기
		
		if(min == 987654321) System.out.print(0);
		else System.out.print(min);

	}//main
	
	// 통나무 옮기기
	private static void move(Queue<int[]> q) {
		int[] end = position[E][1];
		int r, c, d, cnt, nr, nc, d2;
		int[] cur;
		
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0]; // 행
			c = cur[1]; // 열
			d = cur[2]; // 방향 (가로0 세로1)
			cnt = cur[3]; // 이동 횟수
			d2 = d==W ? H : W; // 다른 방향
			
			if(cnt > min) continue;
			
			// 회전할 때 나무 체크
			if(!treeCheck(r, c, d, d2, cnt+1)) {
				if(r==end[0] && c==end[1] && d2==ED) {
					min = Math.min(min, cnt);
					visited[d2][r][c] = visited[d][r][c] + 1;
					continue;
				}
				q.offer(new int[] {r, c, d2, cnt+1});
				visited[d2][r][c] = visited[d][r][c] + 1;
			}//if
			
			for(int i=0; i<4; i++) {
				nr = r + dr[i];
				nc = c + dc[i];
				if(rangeCheck(nr, nc, d, d2, cnt+1)) continue; // 범위, 방문 체크
				if(nr==end[0] && nc==end[1] && d==ED) {
					min = Math.min(min, cnt);
					visited[d][nr][nc] = visited[d][r][c] + 1;	
					continue;
				}
				q.offer(new int[] {nr, nc, d, cnt+1});
				visited[d][nr][nc] = visited[d][r][c] + 1;	
			}//for
			
			
		}//while
		
	}//move : 통나무 옮기기

	// 회전할 때 나무 체크
	private static boolean treeCheck(int r, int c, int d, int d2, int cnt) {
		for(int i=r-1; i<=r+1; i++) {
			if(i<0 || i>=N) return true;
			for(int j=c-1; j<=c+1; j++) {
				if(j<0 || j>=N || map[i][j] == '1') return true;
			}//for j
		}//for i
		return (visited[d2][r][c] > 0 && visited[d2][r][c] <= cnt) ;
	}//treeCheck : 회전할 때 나무 체크

	// 범위 체크
	private static boolean rangeCheck(int r, int c, int d, int d2, int cnt) {
		if(r<0 || r>=N || c<0 || c>=N 
				|| (visited[d][r][c] > 0 && visited[d][r][c] <= cnt) 
				|| map[r][c] == '1') 
			return true;
		
		int r1=r, c1=c, r3=r, c3=c;
		boolean flag = false;
		if(d==W) {
			c1 = c-1;
			c3 = c+1;
			flag = c1<0 || c1>=N || c3<0 || c3>=N;
		}else {
			r1 = r-1;
			r3 = r+1;
			flag = r1<0 || r1>=N || r3<0 || r3>=N;
		}
		return flag || map[r1][c1]=='1' || map[r3][c3]=='1';
	}//rangeCheck : 범위 체크

	// 통나무, 목적지 가로 세로 체크
	private static void directionCheck(Queue<int[]> q) {		
		boolean bw = position[B][0][0] == position[B][1][0]; // 통나무 가로인지
		boolean ew = position[E][0][0] == position[E][1][0]; // 목적지 가로인지
		
		if(!ew) ED = H; // 목적지 세로
		
		int r = position[B][1][0];
		int c = position[B][1][1];
		int d = bw ? W : H;
		q.offer(new int[] {r, c, d, 1}); // 행, 열, 방향, 횟수
		visited[d][r][c] = 1;		
	}//directionCheck : 통나무, 목적지 가로 세로 체크

}//class