package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1717
public class Main_1717_집합의표현 {
	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		for(int i=0; i<=N; i++) {
			parent[i] = i;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(order == 0) { // 합집합
				setUnion(a, b);
			}else if(order == 1) { // 같은 집합에 포함되어 있는지를 확인하는 연산
				if(isSame(a, b)) {
					sb.append("YES\n");
				}else sb.append("NO\n");
			}
		}
		
		System.out.println(sb);

	}

	private static boolean isSame(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		return a==b;
	}

	private static int findParent(int a) {
		if(a==parent[a]) return a;
		return parent[a] = findParent(parent[a]);
	}

	private static void setUnion(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		parent[b] = a;		
	}

}

/*

7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1

*/