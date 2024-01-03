import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1043
public class Main {
	static int N, M;
	static int[] parent;
	static Set<Integer> truth;
	static List<ArrayList<Integer>> party;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 첫째 줄에 사람의 수 N과 파티의 수 M이 주어진다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사람의 수
		M = Integer.parseInt(st.nextToken()); // 파티의 수
		
		init();		
		
		// 둘째 줄에는 이야기의 진실을 아는 사람의 수와 번호가 주어진다.
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		while(K-->0) {
			truth.add(Integer.parseInt(st.nextToken()));
		}//while
		
		int a, b;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken()) - 1;
			
			a = Integer.parseInt(st.nextToken()); 
			party.get(i).add(a);
			
			while(K-->0) {
				b = Integer.parseInt(st.nextToken());
				union(a, b);
				party.get(i).add(b);
			}//while
		}//for
		br.close();
		
		if(truth.isEmpty()) System.out.print(M);
		else {
			int cnt = 0;
			boolean possible;
			for(int i=0; i<M; i++) {
				possible = true;
				for(int n : party.get(i)) {
					if(truth.contains(find(parent[n]))) {
						possible = false;
						break;
					}//if
				}//for n
				
				if(possible) cnt++;
			}//for i
			
			System.out.print(cnt);
		}//else

	}//main

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(truth.contains(b)) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		parent[b] = a;
	}//union

	private static int find(int n) {
		if(parent[n] == n) return n;
		return find(parent[n]);
	}//find

	private static void init() {
		truth = new HashSet<>();
		parent = new int[N+1];
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}//for
		
		party = new ArrayList<>();
		for(int i=0; i<M; i++) {
			party.add(new ArrayList<>());
		}
	}//init

}//class