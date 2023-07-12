import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		int[][] sumArr = new int[N+1][N+1];
		for(int i=1; i<sumArr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<sumArr[0].length; j++) {
				sumArr[i][j] = Integer.parseInt(st.nextToken()) + sumArr[i][j-1];				
			}
		}
		
		for(int k=0; k<M; k++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int sum = 0;
			for(int i=x1; i<=x2; i++) {
				sum += (sumArr[i][y2] - sumArr[i][y1-1]);				
			}
			sb.append(sum + "\n");
		}
		
		System.out.println(sb);
		br.close();
	}//main

}//class