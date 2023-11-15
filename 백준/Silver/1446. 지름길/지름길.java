import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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

		int from, to, cost;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			if(from > D || to > D) continue;
			list.get(from).add(new Position(to, cost));
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
		int to, cost;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			to = cur.to;
			cost = cur.cost;
			
			if(visited[to]) continue;
			visited[to] = true;
			if(to == D) break;
			
			if(to+1 <= D && min[to+1] > cost + 1) {
				min[to+1] = cost + 1;
				pq.offer(new Position(to+1, min[to+1]));
			}//if
			
			for(Position next : list.get(to)) {
				if(min[next.to] > cost + next.cost || min[next.to] > cost + next.to - to) {
					min[next.to] = Math.min(cost + next.cost, cost + next.to - to);
					pq.offer(new Position(next.to, min[next.to]));
				}//if			
			}//for
			
		}//while
		
	}//getMin

	static class Position implements Comparable<Position>{
		int to;
		int cost;
		public Position(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Position pos) {
			return this.cost - pos.cost;
		}
	}//Position

}//class