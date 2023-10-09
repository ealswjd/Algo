import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1068
public class Main {
	static int N;
	static ArrayList<ArrayList<Integer>> nodeList;
	static boolean[] isRemove;
	static int[] cnt, parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		init();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int node=0; node<N; node++) {
			int p = Integer.parseInt(st.nextToken());
			if(p < 0) continue;
			parent[node] = p;
			nodeList.get(p).add(node);
			cnt[p]++;
		}//for
		
		int D = Integer.parseInt(br.readLine());
		br.close();
		
		isRemove = new boolean[N];
		remove(D);
		
		int leafNodeCnt = 0;
		for(int i=0; i<N; i++) {
			if(!isRemove[i] && cnt[i]==0) leafNodeCnt++;
		}//for
		
		System.out.print(leafNodeCnt);
	}//main

	private static void init() {
		nodeList = new ArrayList<>();
		for(int i=0; i<N; i++) {
			nodeList.add(new ArrayList<>());
		}//for
		
		cnt = new int[N];
		parent = new int[N];
		for(int i=0; i<N; i++) {
			parent[i] = i;
		}//for		
	}//init

	private static void remove(int n) {
		cnt[parent[n]]--;
		isRemove[n] = true;		
		for(int next : nodeList.get(n)) {
			remove(next);
		}//for		
	}//remove

}//class