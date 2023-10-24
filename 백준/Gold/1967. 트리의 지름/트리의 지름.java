import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1967
public class Main {
	static int N, result; // 노드 개수, 트리의 지름
	static ArrayList<ArrayList<int[]>> nodeList; // 노드 리스트
	static ArrayList<ArrayList<Integer>> costList; // 가중치 리스트

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		nodeList = new ArrayList<>();
		costList = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			nodeList.add(new ArrayList<>());
			costList.add(new ArrayList<>());
		}//for

		StringTokenizer st;
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodeList.get(parent).add(new int[] {child, cost});
		}//for
		
		result = 0;
		dfs(1); // 루트 노드 항상 1
		
		System.out.print(result);
	}//main

	private static int dfs(int cur) {
		if(nodeList.get(cur).size() == 0) return 0; // 자식 노드 없음
		
		// 자식 노드
		for(int[] next : nodeList.get(cur)) {
			costList.get(cur).add(dfs(next[0]) + next[1]); // 가중치 얻기
		}//for
		
		// 내림차순
		Collections.sort(costList.get(cur), Collections.reverseOrder());
		
		int sum = costList.get(cur).get(0);
		if(nodeList.get(cur).size() > 1) sum += costList.get(cur).get(1);
		result = Math.max(result, sum); // 지름 갱신
		
		return costList.get(cur).get(0); // 제일 큰 값 반환
	}//dfs


}//class