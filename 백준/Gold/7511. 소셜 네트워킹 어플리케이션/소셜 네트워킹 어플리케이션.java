import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7511
public class Main {
	static int[] parent; // 부모

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		int N, M, K, a, b;
		
		for(int t=1; t<=T; t++) {
			ans.append("Scenario ").append(t).append(":\n");
			N = Integer.parseInt(br.readLine()); // 유저 수
			K = Integer.parseInt(br.readLine()); // 관계 수
			init(N); // 초기화
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				union(a, b); // 친구 연결
			}//for
			
			M = Integer.parseInt(br.readLine()); // 구하고 싶은 쌍의 수
			while(M-->0) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				if(find(a) == find(b)) ans.append(1); // 연결 되어 있으면 1
				else ans.append(0); // 아니면 0
				ans.append("\n");
			}//while
			
			ans.append("\n");
		}//for
		br.close();

		System.out.print(ans);
	}//main

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return;
		
		if(a < b) parent[b] = a;
		else parent[a] = b;		
	}//union

	private static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}//find

	private static void init(int N) {
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}//for
	}//init

}//class