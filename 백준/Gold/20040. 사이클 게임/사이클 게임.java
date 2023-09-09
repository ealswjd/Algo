import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20040
public class Main {
	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 점의 개수
		M = Integer.parseInt(st.nextToken()); // 진행된 차례의 수
		
		init();
		
		int cnt = 0;
		boolean flag = false;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			cnt++;
			if(!union(a, b)) {
				flag = true;
				break;			
			}
		}//while
		br.close();
		
		if(!flag) cnt = 0;
		System.out.print(cnt);
	}//main

	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false;
		
		if(a > b) parent[a] = b;
		else parent[b] = a;
		
		return true;
	}//union

	private static int find(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}//find

	private static void init() {
		parent = new int[N+1];
		for(int i=0; i<=N; i++) {
			parent[i] = i;
		}//for
	}//init

}//class