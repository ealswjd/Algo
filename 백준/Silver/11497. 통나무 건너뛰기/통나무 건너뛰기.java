import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11497
public class Main {
	static int N; // 통나무의 개수
	static int[] heightArr; // 통나무들 높이
	static int min; // 통나무의 높이 차 최솟값

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		while(T-->0) {
			// 통나무 개수
			N = Integer.parseInt(br.readLine());
			// 통나무 높이 입력받기
			heightArr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				heightArr[i] = Integer.parseInt(st.nextToken());
			}//for
			min = 100000; // 1 ≤ Li ≤ 100,000
			// 통나무의 높이 차 최솟값 구하기
			min = Math.min(min, getLevel());
			ans.append(min).append("\n");
		}//while
		br.close();

		System.out.print(ans);
	}//main

	private static int getLevel() {		
		Arrays.sort(heightArr); // 통나무 높이 배열 정렬
		int[] sortArr = new int[N]; // 높이차가 최솟값이 되도록 정렬하기 위한 배열
		int start = 0;
		int end = N-1;
		for(int i=0; i<N; i++) {
			if(i%2 == 0) sortArr[start++] = heightArr[i];
			else sortArr[end--] = heightArr[i];
		}//for
		
		int max = sortArr[N-1] - sortArr[0];
		for(int i=1; i<N; i++) {
			max = Math.max(max, Math.abs(sortArr[i] - sortArr[i-1]));
		}//for
		return max;
	}//getLevel

}//class