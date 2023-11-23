import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 링크 : https://www.acmicpc.net/problem/1446
 * */
public class Main {
	static final int INF = 987654321;
	static int D;
	static List<ArrayList<Position>> list; 
	static int[] min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 지름길의 개수
		D = Integer.parseInt(st.nextToken()); // 고속도로의 길이
		
		list = new ArrayList<>();
		for(int i=0; i<=D; i++) {
			list.add(new ArrayList<>());
		}//for

		int from, to, dist;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken()); // 지름길 시작 위치
			to = Integer.parseInt(st.nextToken()); // 지름길 도착 위치
			dist = Integer.parseInt(st.nextToken()); // 지름길 길이
			// 시작위치나 도착 위치가 목적지보다 크거나, 지름길이 아닌 경우 넘김
			if(from > D || to > D || to-from <= dist) continue; 
			list.get(from).add(new Position(to, dist)); // 모든 지름길은 일방통행
		}//while
		br.close();
		
		min = new int[D+1];
		Arrays.fill(min, INF);
		getMin();
		System.out.print(min[D]);
	}//main
	
	private static void getMin() {
		PriorityQueue<Position> pq = new PriorityQueue<>();
		pq.offer(new Position(0, 0));
		boolean[] visited = new boolean[D+1];
		
		Position cur;
		int to, dist;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			to = cur.to; // 도착 위치
			dist = cur.dist; // 거리 길이
			
			if(visited[to]) continue;
			visited[to] = true;
			
			if(to == D) break; // 목적지 도착
			
			// 1씩 이동
			if(to+1 <= D && min[to+1] > dist + 1) {
				min[to+1] = dist + 1;
				pq.offer(new Position(to+1, min[to+1]));
			}//if
			
			// 지름길 이동
			for(Position next : list.get(to)) {
				if(min[next.to] > dist + next.dist) {
					min[next.to] = dist + next.dist;
					pq.offer(new Position(next.to, min[next.to]));
				}//if			
			}//for
			
		}//while
		
	}//getMin

	static class Position implements Comparable<Position>{
		int to;
		int dist;
		public Position(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
		@Override
		public int compareTo(Position pos) {
			return this.dist - pos.dist;
		}
	}//Position

}//class