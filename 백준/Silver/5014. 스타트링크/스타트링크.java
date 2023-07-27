import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] visited; // 방문 확인
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int F = Integer.parseInt(st.nextToken()); // 전체 층
		int S = Integer.parseInt(st.nextToken()); // 현재 위치
		int G = Integer.parseInt(st.nextToken()); // 목표 층
		int U = Integer.parseInt(st.nextToken()); // 올라갈 수 있는 층 수
		int D = Integer.parseInt(st.nextToken()); // 내려갈 수 있는 층 수
		visited = new int[F+1];
		
		System.out.println(bfs(F, S, G, U, D));
		br.close();
		
	}//main

	private static String bfs(int f, int s, int g, int u, int d) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(s);
		visited[s] = 1;
		
		while(!q.isEmpty()) {
			s = q.poll();
			if(s == g) return visited[s] - 1 + "";
			
			if(s+u <= f && visited[s+u] == 0) {
				q.offer(s+u);
				visited[s+u] = visited[s] + 1;
			}			
			if(s-d >= 1 && visited[s-d] == 0) {
				q.offer(s-d);
				visited[s-d] = visited[s] + 1;
			}			
		}		
		return "use the stairs";		
	}//bfs

}//class