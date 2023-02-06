package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/17406
public class Main_17406_배열돌리기4 {
	static int[][] arr;
	static Rcs[] rcsArr;
	static int N, M, K;
	static int[][] tmpArr;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 배열의 크기 N, M
		M = Integer.parseInt(st.nextToken()); // 배열의 크기 N, M
		K = Integer.parseInt(st.nextToken()); // 회전 연산의 개수
		arr = new int[N][M]; // 기존배열
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		rcsArr = new Rcs[K]; // 회전 연산의 정보 rcs 배열
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			rcsArr[i] = new Rcs(r, c, s); // 배열에 담아주기
		}
		br.close();
		
		boolean[] visited = new boolean[K];
		Rcs[] tmpRcs = new Rcs[K];
		perm(visited, tmpRcs, 0); // 순열 구하기
		
		System.out.println(ans);
	}//main
	

	private static void perm(boolean[] visited, Rcs[] tmpRcs, int n) {
		if(n == K) { // 순열이 만들어질 때마다
			tmpArr = new int[N][M]; // 복사 배열
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					tmpArr[i][j] = arr[i][j];					
				}
			}
			getMin(tmpRcs);
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			tmpRcs[n] = rcsArr[i];
			perm(visited, tmpRcs, n+1);
			visited[i] = false;
		}
		
	}//perm


	private static void getMin(Rcs[] tmpRcs) {
		for(int i=0; i<K; i++) {
			Rcs rcs = tmpRcs[i];			
			rotation(rcs);
		}
		
		int min = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			int sum = 0;
			for(int j=0; j<M; j++) {
				sum += tmpArr[i][j];
			}
			min = Math.min(min, sum);
		}
		
		ans = Math.min(min, ans);		
	}//
	
	private static void rotation(Rcs rcs){
		int x1 = rcs.x1; // 가장 왼쪽 윗 칸 (r-s, c-s)
		int y1 = rcs.y1; 
		int x2 = rcs.x2; // 가장 오른쪽 아랫 칸 (r+s, c+s)
		int y2 = rcs.y2; 
		int gcnt = Math.min(x2 - x1, y2 - y1) / 2; // 그룹 수
		
		for(int g=0; g<gcnt; g++) { // 그룹 수만큼
			int temp = tmpArr[x1+g][y1+g]; // 첫 번째 값 저장
			
			// 좌 (위로 이동)
			for(int j=x1+g; j<x2-g; j++) tmpArr[j][y1+g] = tmpArr[j+1][y1+g];
			// 하 (좌로 이동)
			for(int j=y1+g; j<y2-g; j++) tmpArr[x2-g][j] = tmpArr[x2-g][j+1];
			// 우 (하로 이동)
			for(int j=x2-g; j>x1+g; j--) tmpArr[j][y2-g] = tmpArr[j-1][y2-g];
			// 상 (우로 이동)
			for(int j=y2-g; j>y1+g; j--) tmpArr[x1+g][j] = tmpArr[x1+g][j-1];
			
			tmpArr[x1+g][y1+g+1] = temp; // 저장 했던 값 넣어주기
		}
		
	}//rotation

}//class

class Rcs{
	int r;
	int c;
	int s;
	int x1;
	int y1;
	int x2;
	int y2;
	
	public Rcs(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
		x1 = this.r - this.s - 1;
		y1 = this.c - this.s - 1;
		x2 = this.r + this.s - 1;
		y2 = this.c + this.s - 1;
	}
}
