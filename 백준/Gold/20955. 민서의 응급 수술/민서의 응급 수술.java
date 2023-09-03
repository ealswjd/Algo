import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20955
public class Main {
	static int N;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 뉴런 개수
		int M = Integer.parseInt(st.nextToken()); // 시냅스 개수
		
		init();
		
		int u, v, cnt=0;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			if(!union(u, v)) cnt++;
		}//while
		br.close();
		
		HashSet<Integer> root = new HashSet<>();
		for(int i=1; i<=N; i++) {
			root.add(find(i));
		}//for

		System.out.print(cnt + root.size() - 1);
	}//main

	private static boolean union(int u, int v) {
		u = find(u);
		v = find(v);
		
		if(u == v) return false;
		
		if(u <= v) parent[v] = u;
		else parent[u] = v;
		
		return true;
	}//union

	private static int find(int num) {
		if(parent[num] == num) return num;
		
		return find(parent[num]);
	}//find

	private static void init() {
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}//for
	}//init

}//class