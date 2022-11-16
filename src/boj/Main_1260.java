package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// DFS와 BFS
public class Main_1260 {

	static int n; // 정점의 개수
	static int m; // 간선의 개수
	// 간선이 연결하는 두 정점의 번호 인접리스트
	static ArrayList<ArrayList<Integer>> numList = new ArrayList<>();
	static boolean[] visited; // 방문 확인
	
	public static void main(String[] args) throws Exception {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 정점
		m = Integer.parseInt(st.nextToken()); // 간선
		int v = Integer.parseInt(st.nextToken()); // 시작 정점
		visited = new boolean[n+1];
		
		for(int i=0; i<=n; i++) {
			numList.add(new ArrayList<Integer>());
		}		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			numList.get(x).add(y);
			numList.get(y).add(x);
		}
		br.close();
		/* 방문할 수 있는 정점이 여러 개인 경우에는 
           정점 번호가 작은 것을 먼저 방문하기 위해 정렬 */
		for(int i=0; i<numList.size(); i++) {
			Collections.sort(numList.get(i));
		}
		
		dfs(v);
		visited = new boolean[n+1];
		bfs(v);
	}//main
	
	static void dfs(int v) {
		visited[v] = true;
		System.out.print(v + " ");
		
		for(int i=0; i<numList.get(v).size(); i++) {
			int idx = numList.get(v).get(i);
			if(!visited[idx])  dfs(idx);
		}
	}//dfs
	
	static void bfs(int v) {
		System.out.println();
		Queue<Integer> q = new LinkedList<>();
		visited[v] = true;
		q.add(v);
		
		while(!q.isEmpty()) {
			v = q.poll();
			System.out.print(v + " ");
			
			Iterator<Integer> it = numList.get(v).listIterator();
			while(it.hasNext()) {
				v = it.next();
				if(!visited[v]) {
					visited[v] = true;
					q.add(v);
				}
			}
		}
	}//bfs

}//class
