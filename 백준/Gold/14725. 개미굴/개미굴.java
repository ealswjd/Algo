import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14725
public class Main {
	static StringBuilder ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ans = new StringBuilder();			
		
		Food root = new Food(null);
		StringTokenizer st;
		int cnt;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			cnt = Integer.parseInt(st.nextToken());
			Food cur = root;
			while(cnt-->0) {
				String key = st.nextToken();
				boolean flag = false;
				
				for(Food next : cur.edge) {
					if(next.key.equals(key)) {
						cur = next;
						flag = true;
						break;
					}//if
				}//for
				
				if(!flag) {
					Food newFood = new Food(key);
					cur.edge.add(newFood);
					cur = newFood;
				}//if
			}//while
			
		}//while

		dfs(root, 0);
		System.out.print(ans);		
	}//main
	
	private static void dfs(Food cur, int depth) {
		Collections.sort(cur.edge);
		for(Food next : cur.edge) {
			for(int i=0; i<depth; i++) {
				ans.append("--");
			}//for
			ans.append(next.key).append('\n');
			dfs(next, depth+1);
		}//for
	}//dfs

	static class Food implements Comparable<Food>{
		String key;
		List<Food> edge;
		public Food(String key) {
			this.key = key;
			edge = new ArrayList<>();
		}
		@Override
		public int compareTo(Food o) {
			return this.key.compareTo(o.key);
		}
	}//Food

}//class