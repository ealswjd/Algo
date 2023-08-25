import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static List<Position> homeList;
	static List<Position> cList;
	static Position[] arr;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		homeList = new ArrayList<>(); 
		cList = new ArrayList<>(); 
		arr = new Position[M];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) homeList.add(new Position(i, j));
				if(map[i][j] == 2) cList.add(new Position(i, j));
			}
		}
		
		comb(0,0);
		System.out.println(result);
	}//main
	
	private static void comb(int cnt, int start) {
		if(cnt == M) {
			getMin();
			return;
		}
		
		for(int i=start; i<cList.size(); i++) {
			arr[cnt] = cList.get(i);
			comb(cnt+1, i+1);
		}
		
	}//comb

	private static void getMin() {
		int sum = 0;
		for(int i=0; i<homeList.size(); i++) {
			int min = Integer.MAX_VALUE;
			int x1 = homeList.get(i).x;
			int y1 = homeList.get(i).y;
			for(int j=0; j<arr.length; j++) {
				int x2 = arr[j].x;
				int y2 = arr[j].y;
				int dist = Math.abs(x2-x1) + Math.abs(y1-y2);
				min = Math.min(min, dist);
			}
			sum += min;
		}
		result = Math.min(result, sum);
	}//getMin

}//class

class Position{
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}