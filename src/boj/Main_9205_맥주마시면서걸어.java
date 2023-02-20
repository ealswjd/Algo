package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9205
public class Main_9205_맥주마시면서걸어 {
	static int N;
	static ArrayList<ArrayList<Integer>> posList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder(); // 출력
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine()); // 맥주를 파는 편의점의 개수
			ArrayList<Position> list = new ArrayList<>(); // 상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표
			
			// n+2개 줄에는 상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표가 주어진다. 
			for(int i=0; i<N+2; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				list.add(new Position(r, c));
			}			
			
			posList = new ArrayList<>(); // 갈 수 있는 좌표 리스트
			for(int i=0; i<N+2; i++) {
				posList.add(new ArrayList<>());
			}
			
			for(int i=0; i<N+2; i++) {
				for(int j=i+1; j<N+2; j++) {
					if(isPossible(list.get(i), list.get(j))) { // 맨해튼 거리 확인
						posList.get(i).add(j);
						posList.get(j).add(i);
					}
				}
			}
			
			if(bfs()) sb.append("happy");
			else sb.append("sad");
			
			sb.append("\n");
			
		}//for tc
		
		// 각 테스트 케이스에 대해서 상근이와 친구들이 행복하게 페스티벌에 갈 수 있으면 "happy", 
		// 중간에 맥주가 바닥나서 더 이동할 수 없으면 "sad"를 출력한다. 
		System.out.print(sb);
	}//main

	
	private static boolean bfs() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+2];
		q.offer(0); // 상근이 집
		visited[0] = true;

		while(!q.isEmpty()) {
			int now = q.poll();
			if(now == N+1) return true; // 페스티벌 도착 해피
			
			for(int next : posList.get(now)) { // 현재 위치에서 갈 수 있는 좌표로 이동
				if(visited[next]) continue; // 방문체크
				visited[next] = true;
				q.offer(next);
			}
		}//while
		
		return false; // 도착 못해서 슬픔
	}//bfs


	// 50미터에 한 병씩, 맥주는 20병을 넘을 수 없다. ( 50 * 20 ) 
	private static boolean isPossible(Position pos1, Position pos2) {
		return (Math.abs(pos1.r - pos2.r) + Math.abs(pos1.c-pos2.c)) <= 1000 ;
	}//isPossible


	static class Position{
		int r;
		int c;
		
		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}		
	}//Position
}//class
