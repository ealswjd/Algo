import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5549
public class Main {
	static final char JUNGLE='J', OCEAN='O', ICE='I';
	static int N, M;
	static int[][][] map;
	static int[][][] cnt;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine()); // 조사 대상 영역의 개수
		
		map = new int[N+1][M+1][3];
		cnt = new int[N+1][M+1][3];
		int idx;
		for(int i=1; i<=N; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=1; j<=M; j++) {
				idx = getIdx(tmp[j-1]);
				map[i][j][idx] = 1;
			}//for j
		}//for i
		
		// 누적합
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				for(idx=0; idx<3; idx++) {
					cnt[i][j][idx] = cnt[i-1][j][idx] + cnt[i][j-1][idx] - cnt[i-1][j-1][idx] + map[i][j][idx];
					map[i][j][idx] = cnt[i][j][idx];
				}
			}//for j
		}//for i	
		
		StringBuilder ans = new StringBuilder();
		int r1, c1, r2, c2;
		while(K-->0) {
			st = new StringTokenizer(br.readLine());
			// 왼쪽 위 모서리의 좌표
			r1 = Integer.parseInt(st.nextToken());
			c1 = Integer.parseInt(st.nextToken());
			// 오른쪽 아래 모서리의 좌표
			r2 = Integer.parseInt(st.nextToken());
			c2 = Integer.parseInt(st.nextToken());
			
			// 정글, 바다, 얼음
			for(int i=0; i<3; i++) {
				ans.append(cnt[r2][c2][i] - cnt[r2][c1-1][i] - cnt[r1-1][c2][i] + cnt[r1-1][c1-1][i])
				.append(' ');				
			}//for
			
			ans.append('\n');
		}//while

		System.out.print(ans);
	}//main
	
	private static int getIdx(char c) {
		switch (c) {
		case JUNGLE: return 0; // 정글
		case OCEAN: return 1; // 바다
		default: return 2; // 얼음
		}
	}//getIdx

}//class