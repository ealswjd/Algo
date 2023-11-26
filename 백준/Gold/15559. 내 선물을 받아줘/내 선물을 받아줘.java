import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15559
public class Main {
	static int N, M;
	static int[][][] map;
	static int[] parent;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M][2];
		
		for(int i=0; i<N; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				map[i][j][0] = getDir(tmp[j]);
			}//for
		}//for
		br.close();
		
		parent = new int[N*M];
		for(int i=0, len=N*M; i<len; i++) {
			parent[i] = i;
		}//for
		
		makeGroup();
		System.out.print(getCnt());
	}//main

	private static int getCnt() {
		Set<Integer> set = new HashSet<>();
		
		for(int[][] tmp : map) {
			for(int[] n : tmp) {
				set.add(find(n[1]));
			}//for n
		}//for tmp
		
		return set.size();
	}//getCnt

	private static void makeGroup() {
		int cur, next, dir, nr, nc;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				dir = map[r][c][0];
				cur = r * M + c;
				nr = r + dr[dir];
				nc = c + dc[dir];
				next = nr * M + nc;
				
				map[r][c][1] = map[nr][nc][1] = union(cur, next);
			}//for c
		}//for r
		
	}//makeGroup

	private static int union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return a;
		
		if(a < b) parent[b] = a;
		else parent[a] = b;
		
		return parent[a];
	}//union

	private static int find(int n) {
		if(parent[n] == n) return n;
		return find(parent[n]);
	}//find

	private static int getDir(char c) {				
		switch (c) {
		case 'N': return 0;
		case 'S': return 1;
		case 'W': return 2;
		default: return 3;
		}//switch
	}//getDir

}//class