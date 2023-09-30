import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12931
public class Main {
	static int plusCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 배열의 크기
		int doubleCnt = 0; // 두 배 연산 횟수
		plusCnt = 0; // 증가 연산 횟수
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num;
		while(N-->0) {
			num = Integer.parseInt(st.nextToken());
			int tmp = makeZero(num);
			doubleCnt = Math.max(tmp, doubleCnt);
		}//while

		System.out.print(plusCnt + doubleCnt);
	}//main

	private static int makeZero(int num) {
		int doubleCnt = 0;
		
		while(num > 0) {
			switch (num % 2) {
			case 0:
				doubleCnt++;
				num /= 2;
				break;
			case 1:
				plusCnt++;
				num--;
				break;
			}//switch
		}//while
		
		return doubleCnt;
	}//makeZero

}//class