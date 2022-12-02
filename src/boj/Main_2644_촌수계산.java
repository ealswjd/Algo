package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2644_촌수계산 {
	static ArrayList<ArrayList<Integer>> list;
	static int[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		int N = Integer.parseInt(br.readLine()); // 전체 사람의 수
		
		visited = new int[N];
		list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			list.add(new ArrayList<>());
		}
		
		// 촌수를 계산해야 하는 서로 다른 두 사람의 번호가 주어진다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int p1 = Integer.parseInt(st.nextToken()) - 1;
		int p2 = Integer.parseInt(st.nextToken()) - 1;
		
		// 부모 자식들 간의 관계의 개수 m이 주어진다.
		int m = Integer.parseInt(br.readLine());		
		// 부모 자식간의 관계를 나타내는 두 번호 x,y가 각 줄에 나온다. 
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			list.get(x).add(y);
			list.get(y).add(x);
		}

		System.out.print(bfs(p1, p2)); // 촌수 출력
	}//main

	private static int bfs(int p1, int p2) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(p1);
		visited[p1] = 1;
		
		while(!q.isEmpty()) {
			int p = q.poll();
			
			if(p == p2) return visited[p] - 1; // 촌수를 계산할 사람을 만났으면 촌수 리턴
			
			for(int i=0; i<list.get(p).size(); i++) {
				int next = list.get(p).get(i); // 다음
				if(visited[next] == 0) { // 방문한적 없으면
					q.offer(next); 
					visited[next] = visited[p]+1; // 촌수 계산
				}
			}
		}//while
		
		return -1; // 촌수를 계산할 수 없음
	}//bfs

}//class

/*

9
7 3
7
1 2
1 3
2 7
2 8
2 9
4 5
4 6
====>3

*/