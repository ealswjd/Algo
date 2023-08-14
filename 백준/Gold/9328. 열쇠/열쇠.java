import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9328
public class Main {
	static int H, W, dcCnt;
	static char[][] map;
	static boolean[][] visited;
	static Queue<int[]> q;
	static Map<Character, Character> keys;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static List<int[]> closeList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		q = new LinkedList<int[]>();
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			visited = new boolean[H][W];
			keys = new HashMap<>();
			closeList = new LinkedList<>();
			dcCnt = 0;
			
			for(int i=0; i<H; i++) {
				char[] tmp = br.readLine().toCharArray();
				for(int j=0; j<W; j++) {
					map[i][j] = tmp[j];
				}//for j
			}//for i

			char[] keyArr = br.readLine().toCharArray();
			if(keyArr[0] != '0') {
				for(int i=0; i<keyArr.length; i++) {
					keys.put(Character.toUpperCase(keyArr[i]), keyArr[i]);
				}//for				
			}//if
			
			int keyCnt = keys.size();			
			visited = new boolean[H][W];
			getIn();
			
			while(!q.isEmpty()) {
				getDocument();
				if(keyCnt != keys.size()) {
					getIn();
					open();
					keyCnt = keys.size();
				}//if
			}//while
			
			ans.append(dcCnt).append("\n");
		}//while
		br.close();
		
		System.out.print(ans);
	}//main

	
	private static void open() {
		int r, c;
		for(int[] close : closeList) {
			r = close[0];
			c = close[1];
			if(containsKey(map[r][c])) {
				q.offer(new int[] {r, c});		
				map[r][c] = '.';
				visited[r][c] = true;
			}
		}//for
		
	}//open


	private static void getDocument() {
		int r, c;
		int[] cur;
		char key;
		while(!q.isEmpty()) {
			cur = q.poll();
			r = cur[0];
			c = cur[1];
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(rangeCheck(nr, nc) || visitedCheck(nr, nc)) continue;
				key = map[nr][nc];
				visited[nr][nc] = true;
				
				if(isDoor(key) && !containsKey(key)) {
					closeList.add(new int[] {nr, nc});
					continue;
				} else if(isKey(key)) {
					keys.put(Character.toUpperCase(key), key);
					map[nr][nc] = '.';
				} else if(key == '$') {
					map[nr][nc] = '.';
					dcCnt++;
				} 
				
				q.offer(new int[] {nr, nc});
			}//for
		}//while
		
	}//getDocument


	private static void getIn() {		
		char c;
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(!edge(i, j) || visitedCheck(i, j)) continue;
				c = map[i][j];
				visited[i][j] = true;
				
				if(isDoor(c)) { // 문
					if(!containsKey(c)) {
						closeList.add(new int[] {i, j});
						continue;
					}
					map[i][j] = '.';				
				} else if(c == '$') { // 문서
					dcCnt++;
					map[i][j] = '.';
				} else if(isKey(c)) { // 열쇠
					keys.put(Character.toUpperCase(c), c);
					map[i][j] = '.';
				}//else
				
				q.offer(new int[] {i, j});
			}//j
		}//i
		
	}//getIn


	private static boolean edge(int r, int c) {
		return r == 0 || r == H-1 || c == 0 || c == W-1;
	}//edge
	
	private static boolean isKey(char key) {
		return 'a' <= key && key <= 'z';
	}//isKey

	private static boolean containsKey(char key) {
		return keys.containsKey(key);
	}//containsKey

	private static boolean visitedCheck(int r, int c) {
		return visited[r][c] || map[r][c] == '*';
	}//visitedCheck
	
	private static boolean rangeCheck(int r, int c) {
		return r < 0 || r >= H || c < 0 || c >= W;
	}//rangeCheck
	
	private static boolean isDoor(char c) {
		return 'A' <= c && c <= 'Z';
	}//isDoor

}//class
