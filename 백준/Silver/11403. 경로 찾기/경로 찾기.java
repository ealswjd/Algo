import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11403
public class Main {
	static int N; // 정점의 개수 N
	static int[][] result; // 정답
	static boolean[] visited; // 방문 체크
	static ArrayList<ArrayList<Integer>> list; // 연결 정보

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		
		// 첫째 줄에 정점의 개수 N (1 ≤ N ≤ 100)이 주어진다.
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N]; // // 그래프의 인접 행렬
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());
		}
		
		// 둘째 줄부터 N개 줄에는 그래프의 인접 행렬이 주어진다.
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) list.get(i).add(j);
			}//for j
		}//for i
		
		result = new int[N][N]; // 정답 
		for(int i=0; i<N; i++) {
			visited = new boolean[N]; // 방문 배열 초기화
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) { // 이동 가능하면
					dfs(i, j); // 경로 그래프 그리러 가
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(result[i][j] + " ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}//main

	private static void dfs(int r, int c) {
		result[r][c] = 1; // 이동 가능 체크
		
		for(int i=0, size=list.get(c).size(); i<size; i++) {
			int v = list.get(c).get(i);
			if(!visited[v]) { // 방문 체크
				visited[v] = true;
				dfs(r, v); // 다음 정점 이동
			}//if			
		}//for
		
	}//dfs

}//class