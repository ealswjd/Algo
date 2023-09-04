import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24230
public class Main {
	static int N;
	static int[] color;
	static boolean[] checked;
	static ArrayList<ArrayList<Integer>> parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		color = new int[N];
		checked = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			color[i] = Integer.parseInt(st.nextToken());
		}//for
		
		parent = new ArrayList<>();
		for(int i=0; i<N; i++) {
			parent.add(new ArrayList<>());
		}//for

		for(int i=1,a=0,b=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			parent.get(a).add(b);
			parent.get(b).add(a);
		}//for
		br.close();

		checked[0] = true;
		int cnt = color[0] > 0 ? 1 : 0;
		cnt = getCnt(0, cnt);
		
		System.out.print(cnt);
	}//main
	
	private static int getCnt(int cur, int cnt) {
		
		for(int next : parent.get(cur)) {
			if(checked[next]) continue;
			checked[next] = true;
			if(color[cur] != color[next] && color[next] != 0) cnt = getCnt(next, cnt+1);
			else cnt = getCnt(next, cnt);
		}//for
		
		return cnt;
	}//getCnt


}//class