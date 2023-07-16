import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16562
public class Main {
	static int N, K, min; // 학생 수 N, 가지고 있는 돈 K, 최소비용 min
	static int[] cost; // 각각의 학생이 원하는 친구비
	static int[] union; // 친구 소속

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		int M = Integer.parseInt(st.nextToken()); // 친구관계 수
		K = Integer.parseInt(st.nextToken()); // 가지고 있는 돈
		
		cost = new int[N+1]; // 친구비
		union = new int[N+1]; // 친구 소속
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			union[i] = i;
		}//for
		
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(find(a) != find(b)) union(a, b);
		}//while
		br.close();
		
		StringBuilder ans = new StringBuilder();
		if(friendCheck()) ans.append(min);
		else ans.append("Oh no");
		
		System.out.print(ans);
	}//main

	private static boolean friendCheck() {
		int[] minCost = new int[N+1]; // 최소비용
		boolean[] parent = new boolean[N+1]; // 대표인지 확인
		Arrays.fill(minCost, K+1);
		
		for(int i=1; i<=N; i++) {
			parent[union[i]] = true;
			minCost[union[i]] = Math.min(minCost[union[i]], cost[i]); // 최소비용 갱신
		}//for				
		
		min = 0;
		for(int i=1; i<=N; i++) {
			if(parent[i]) min += minCost[i]; // 친구비 갱신
			if(min > K) return false; // 친구비 모자라서 친구 못사귐
		}//for

		return true; // 친구 사귈 수 있음
	}//friendCheck

	// 친구 연결
	private static void union(int a, int b) {
		int A = find(a);
		int B = find(b);	
	
		if(union[a] != a && cost[union[a]] <= cost[A]) A = union[a];
		if(union[b] != b && cost[union[b]] <= cost[B]) B = union[b];
		
		if(cost[A] <= cost[B]) {
			union[B] = A;
			for(int i=1; i<=N; i++) {
				if(union[i] == B) union[i] = A;
			}//for
		} else {
			union[A] = B;
			for(int i=1; i<=N; i++) {
				if(union[i] == A) union[i] = B;
			}//for
		}//else
		
	}//union

	// 친구 소속 확인
	private static int find(int a) {
		if(union[a] == a) return a;
		return union[a] = find(union[a]);
	}//find

}//class