import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3584
public class Main {
	static int N;
	static int[] parent;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		int A, B, P;
		while(T-->0) {
			N = Integer.parseInt(br.readLine()); // 노드의 수
			init();
			
			for(int i=1; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				// A가 B의 부모
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				parent[B] = A;
			}//for
			
			// 가장 가까운 공통 조상을 구할 두 노드
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			if(A == B) ans.append(parent[A]).append("\n");
			else {
				dfs(A);
				P = dfs(B);
				ans.append(P).append("\n");				
			}//else
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static int dfs(int n) {
		if(visited[n] || parent[n] == 0) return n;
		visited[n] = true;

		return dfs(parent[n]);
	}//dfs

	private static void init() {
		parent = new int[N+1];
		visited = new boolean[N+1];	
	}//init

}//class