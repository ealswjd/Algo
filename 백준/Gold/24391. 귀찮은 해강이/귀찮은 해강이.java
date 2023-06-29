import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int prev;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
		
		int a, b;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if(find(a) != find(b)) union(a, b);
		}//while
		
		st = new StringTokenizer(br.readLine());
		br.close();
		N--;
		int prev = Integer.parseInt(st.nextToken());
		int cnt = 0, cur;
		while(N-->0) {
			prev = find(prev);
			cur = find(Integer.parseInt(st.nextToken()));
			if(cur != prev) cnt++;
			prev = cur;
		}//while
		
		System.out.print(cnt);
	}//main

	private static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		
		if(A == B) return;
		
		if(A > B) parent[A] = B;
		else parent[B] = A;
	}//union
	
	private static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}//find

}//class
