import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4195
public class Main {
	static int cnt;
	static HashMap<String, Integer> map;
	static HashMap<Integer, Integer> cntMap;
//	static ArrayList<Integer> parent;
	static int[] parent;
	static StringBuilder ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		ans = new StringBuilder();
		StringTokenizer st;
		while(T-->0) {
			int F = Integer.parseInt(br.readLine());
			parent = new int[F*2];
			for(int i=1,len=F*2; i<len; i++) {
				parent[i] = i;
			}
			map = new HashMap<>();
			cntMap = new HashMap<>();
			
			cnt = 0;
			while(F-->0) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!map.containsKey(a)) map.put(a, cnt++);
				if(!map.containsKey(b)) map.put(b, cnt++);
				
				int key = union(map.get(a), map.get(b));				
				ans.append(cntMap.get(key)).append("\n");
			}//while
			
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static int union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return a;

		if(a < b) {
			parent[b] = a;
			cntMap.put(a, cntMap.getOrDefault(a, 1) + cntMap.getOrDefault(b, 1));
			cntMap.put(b, cntMap.get(a));
			return a;
		}else {
			parent[a] = b;
			cntMap.put(b, cntMap.getOrDefault(b, 1) + cntMap.getOrDefault(a, 1));
			cntMap.put(a, cntMap.get(b));
			return b;
		}
	}//union

	private static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}//find

}//class