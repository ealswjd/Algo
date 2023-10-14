import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16964
public class Main {
	static int N;
	static ArrayList<ArrayList<Integer>> list;
	static int[] order, parent;
	static boolean[] visited;
	static boolean end;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 정점의 수
		
		order = new int[N]; // 방문 순서
		visited = new boolean[N+1]; // 방문 체크
		parent = new int[N+1]; // 부모 정보
		
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>()); 
		}//for
		
		StringTokenizer st;
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}//for
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			order[i] = Integer.parseInt(st.nextToken()); // 방문 순서 입력
		}//for
		br.close();
		
		end = false;
		if(dfs(1, 0)) System.out.print(1);
		else System.out.print(0);
	}//main

	private static boolean dfs(int cur, int idx) {
		if(end) return false;
		
		visited[cur] = true;
		
		int cnt = 0;
		for(int next : list.get(cur)) {
			if(visited[next]) continue;
			visited[next] = true;
			parent[next] = cur;
			cnt++;
		}//for
		
		idx++;
		for(int i=0; i<cnt; i++) {
			int next = order[idx];
			if(parent[next] != cur) {
				end = true;
				return false;
			}//if
			if(!dfs(next, idx)) return false;			
		}//for
		
		return true;
	}//dfs


}//class