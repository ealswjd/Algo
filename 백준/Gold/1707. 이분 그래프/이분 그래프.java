import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {	
	static ArrayList<ArrayList<Integer>> numList;
	static int[] visited;
	static boolean flag;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		
		StringBuilder answer = new StringBuilder();
		for(int i=0; i<k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int vCnt = Integer.parseInt(st.nextToken());
			int eCnt = Integer.parseInt(st.nextToken());
			numList = new ArrayList<>();
			visited = new int[vCnt + 1];
			
			for(int j=0; j<=vCnt; j++) {
				numList.add(new ArrayList<Integer>());
			}
			for(int j=0; j<eCnt; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				numList.get(x).add(y);
				numList.get(y).add(x);
			}
			flag = true;
			for(int v=1; v<=vCnt; v++) {
				if(!flag) break;
				if(visited[v] == 0) bfs(v, 1);
			}
			answer.append(flag ? "YES\n" : "NO\n");
		}
		br.close();		
		System.out.print(answer);
	}//main
	
	static void bfs(int v, int color) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(v);
		visited[v] = color;
		
		while(!q.isEmpty() && flag) {
			v = q.poll();
			for(int nv : numList.get(v)) {
				if(visited[nv] == 0) {
					visited[nv] = visited[v] * -1;
					q.offer(nv);
				}else if(visited[nv] == visited[v]){
					flag = false;
					return;
				}
			}
		}//while		
	}//bfs
	
}//class