import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2310
public class Main {
	static int N;
	static final int E=0, L=1, T=2; 
	static StringBuilder ans;
	static int[][] hostInfo;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> roomList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ans = new StringBuilder();
		StringTokenizer st;
		while(true) {
			N = Integer.parseInt(br.readLine()); // 미로의 방 수
			if(N == 0) break;
			
			roomList = new ArrayList<>();
			for(int i=0; i<=N; i++) {
				roomList.add(new ArrayList<>());
			}//for
			
			hostInfo = new int[N+1][2];
			visited = new boolean[N+1];
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());

				switch (st.nextToken()) {
				case "E": hostInfo[i][0] = E;
					break;
				case "L": hostInfo[i][0] = L;
					break;
				case "T": hostInfo[i][0] = T;
					break;
				}//switch
				
				hostInfo[i][1] = Integer.parseInt(st.nextToken());
				int num;
				while(true) {
					num = Integer.parseInt(st.nextToken());
					if(num == 0) break;
					roomList.get(i).add(num);
				}//while
			}//for
			
			gameStart(1); // 1번 방에서 n번 방까지
			ans.append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static void gameStart(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		
		int cash = 0; // 모험가의 소지금
		int cur, cost, host;
		game:while(!q.isEmpty()) {
			cur = q.poll(); // 현재 방
			host = hostInfo[cur][0]; // 현재 방 주인
			cost = hostInfo[cur][1]; // 현재 방 비용
			
			if(cur == N) { // n번 방
				ans.append("Yes");
				return;
			}//if
			
			switch (host) {
			case L: // 레프리콘 - 돈 충전
				if(cash < cost) cash = cost;
				break;
			case T: // 트롤 - 돈 뺏음
				if(cash < cost) break game;
				cash -= cost;
				break;
			}//switch
			
			for(int next : roomList.get(cur)) {
				if(visited[next] || (hostInfo[next][0]==T && hostInfo[next][1] > cash)) continue;
				visited[next] = true;
				q.offer(next);
			}//for
			
		}//while
		
		ans.append("No");
	}//gameStart

}//class