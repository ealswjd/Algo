import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10282
public class Main {
	static int N, C;
	static ArrayList<ArrayList<Computer>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
			int D = Integer.parseInt(st.nextToken()); // 의존성 개수
			C = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터 번호
			
			// list 초기화
			list = new ArrayList<>();
			for(int i=0; i<=N; i++) {
				list.add(new ArrayList<>());
			}//for
			
			while(D-->0) {
				st = new StringTokenizer(br.readLine());
				// 컴퓨터 a가 컴퓨터 b를 의존하며, 컴퓨터 b가 감염되면 s초 후 컴퓨터 a도 감염됨
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s= Integer.parseInt(st.nextToken());
				list.get(b).add(new Computer(a, s));
			}//while
			
			dijkstra(ans);
			ans.append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static void dijkstra(StringBuilder ans) {
		PriorityQueue<Computer> pq = new PriorityQueue<>();
		pq.offer(new Computer(C, 0));
		boolean[] visited = new boolean[N+1];
		int[] min = new int[N+1];
		Arrays.fill(min, 987654321);
		
		Computer cur;
		int totalCnt=0, totalTime=0;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			
			if(visited[cur.to]) continue;
			visited[cur.to] = true;
			
			totalTime = cur.time;
			totalCnt++;
			
			for(Computer next : list.get(cur.to)) {
				if(!visited[next.to] && min[next.to] > cur.time + next.time) {
					min[next.to] = cur.time + next.time;
					pq.offer(new Computer(next.to, min[next.to]));
				}//if
			}//for
		}//while
		
		ans.append(totalCnt).append(" ").append(totalTime);		
	}//dijkstra

	static class Computer implements Comparable<Computer> {
		int to;
		int time;
		public Computer(int to, int time) {
			this.to = to;
			this.time = time;
		}
		@Override
		public int compareTo(Computer c) {
			return this.time - c.time;
		}
	}//Computer
}//class