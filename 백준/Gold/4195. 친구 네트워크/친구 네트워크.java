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
	static ArrayList<Integer> parent;
	static StringBuilder ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		ans = new StringBuilder();
		StringTokenizer st;
		while(T-->0) {
			int F = Integer.parseInt(br.readLine());
			parent = new ArrayList<>();
			
			map = new HashMap<>();
			cntMap = new HashMap<>();
			
			cnt = 0;
			while(F-->0) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!map.containsKey(a)) map.put(a, cnt++);
				if(!map.containsKey(b)) map.put(b, cnt++);
				
				for(int i=parent.size(); i<cnt; i++) {
					parent.add(i);
				}//for
				
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
			parent.set(b, a);
			cntMap.put(a, cntMap.getOrDefault(a, 1) + cntMap.getOrDefault(b, 1));
			return a;
		}else {
			parent.set(a, b);
			cntMap.put(b, cntMap.getOrDefault(b, 1) + cntMap.getOrDefault(a, 1));
			return b;
		}
	}//union

	private static int find(int n) {
		if(parent.get(n).equals(n)) return n; 
		
		parent.set(n, find(parent.get(n))); // 부모 변경******
		return parent.get(n); 
	}//find

}//class