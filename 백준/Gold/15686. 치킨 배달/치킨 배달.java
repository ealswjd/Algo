import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15686
public class Main {
	static int N, M;
	static int[][] map, chickens;
	static List<int[]> homeList, cList;
	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시의 크기
		M = Integer.parseInt(st.nextToken()); // 치킨집 최대 개수
		
		init();
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				switch (map[i][j]) {
				case 0: continue;
				case 1: // 집
					homeList.add(new int[] {i, j});
					break;
				case 2: // 치킨집
					cList.add(new int[] {i, j});
					break;
				}//switch
			}//for j
		}//for i

		min = 987654321; // 도시의 치킨 거리의 최솟값 
		comb(0, 0); // 치킨집 비교해보기
		System.out.print(min);
	}//main

	private static void comb(int start, int cnt) {
		if(cnt == M) { // 치킨집 M개 탐색 끝
			getMin(); // 최솟값 구하기
			return;
		}//if
		
		for(int i=start, size=cList.size(); i<size; i++) {
			chickens[cnt] = cList.get(i);
			comb(i+1, cnt+1);
		}//for		
	}//comb

	private static void getMin() {
		int sum = 0; // 도시의 치킨 거리
		
		for(int[] home : homeList) { // 집
			int minDist = 987654321; // 가까운 치킨거리
			int r1 = home[0]; // 집 행
			int c1 = home[1]; // 집 열
			for(int[] chicken : chickens) { // 치킨집
				int r2 = chicken[0]; // 치킨 행
				int c2 = chicken[1]; // 치킨 열
				int dist = Math.abs(r1-r2) + Math.abs(c1-c2); // 치킨 거리
				minDist = Math.min(minDist, dist); // 가까운 치킨 거리 갱신
			}//for chicken
			sum += minDist; // 도시의 치킨 거리 합산
		}//for home
		
		min = Math.min(min, sum); // 도시의 치킨 거리의 최솟값 
	}//getMin

	private static void init() {
		map = new int[N+1][N+1]; // 크기가 N×N인 도시
		chickens = new int[M][2]; // 폐업시지지 않을 치킨집
		homeList = new ArrayList<>(); // 집 정보 리스트
		cList = new ArrayList<>(); // 치킨집 정보 리스트
	}//init

}//class