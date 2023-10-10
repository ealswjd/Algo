import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1719
public class Main {
	static final int INF = 200 * 10000;
	static int N;
	static int[][] map;
	static int[][] result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 집하장의 개수
		int M = Integer.parseInt(st.nextToken()); // 집하장간 경로의 개수
		
		init();		

		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			map[a][b] = map[b][a] = cost;
		}//while
		br.close();
		
		for(int k=1; k<=N; k++) {
			for(int from=1; from<=N; from++) {
				for(int to=1; to<=N; to++) {
					if(map[from][to] > map[from][k] + map[k][to]) {
						map[from][to] = map[from][k] + map[k][to];
						result[from][to] = result[from][k];
					}
				}//for to
			}//for from
		}//for k

		print();
	}//main

	private static void print() {
		StringBuilder ans = new StringBuilder();
		
		for(int from=1; from<=N; from++) {
			for(int to=1; to<=N; to++) {
				if(from == to) ans.append("-");
				else ans.append(result[from][to]);
				ans.append(" ");
			}//for to
			ans.append("\n");
		}//for from
		
		System.out.print(ans);
	}//print

	private static void init() {
		map = new int[N+1][N+1];
		result = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(map[i], INF);
		}//for
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				result[i][j] = j;
			}//for j
		}//for i
		
	}//init

	
}//class