import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 강의의 개수
		int M = Integer.parseInt(st.nextToken()); // 연결되어 있는 건물의 쌍의 개수
		
		parent = new int[N+1]; // 같은 건물
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}//for
		
		int a, b;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()); // 건물 1
			b = Integer.parseInt(st.nextToken()); // 건물 2
			
			union(a, b); // 같은 건물 처리
		}//while
		
		st = new StringTokenizer(br.readLine());
		br.close();
		N--;
		int prev = Integer.parseInt(st.nextToken()); 
		int cnt = 0, cur;
		while(N-->0) {
			prev = find(prev); // 이전 수업 건물
			cur = find(Integer.parseInt(st.nextToken())); // 현재 수업 건물
			if(cur != prev) cnt++; // 건물 다르면 횟수 증가
			prev = cur;
		}//while
		
		System.out.print(cnt);
	}//main

	// 같은 건물 처리
	private static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		
		if(A == B) return;
		
		if(A > B) parent[A] = B;
		else parent[B] = A;
	}//union
	
	// 같은 건물인지 확인
	private static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}//find

}//class
