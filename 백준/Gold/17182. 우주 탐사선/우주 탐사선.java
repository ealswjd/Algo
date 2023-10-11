import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17182
public class Main {
	static int N, K, min;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행성의 개수
		K = Integer.parseInt(st.nextToken()); // 행성의 위치
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				map[i][j] = cost;
			}//for j
		}//for i
		br.close();

		for(int k=0; k<N; k++) {
			for(int from=0; from<N; from++) {
				for(int to=0; to<N; to++) {
					if(map[from][to] > map[from][k] + map[k][to]) {
						map[from][to] = map[from][k] + map[k][to];
					}//if
				}//for to
			}//for from
		}//for k
		
		min = 987654321;
		getMin(K, 0, (1<<K));
		System.out.print(min);
	}//main

	private static void getMin(int start, int time, int visited) {
		if(visited == (1<<N) - 1) {
			min = Math.min(min, time);
			return;
		}//if
		
		for(int i=0; i<N; i++) {
			if((visited & (1<<i)) != 0) continue;
			getMin(i, time+map[start][i], visited | (1<<i));
		}//for
		
	}//getMin

}//class