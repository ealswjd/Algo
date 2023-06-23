import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 연병장의 크기 N
		int M = Integer.parseInt(st.nextToken()); // 조교의 수 M
		
		int[] H = new int[N]; // 연병장 각 칸의 높이
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			H[i] = Integer.parseInt(st.nextToken());
		}//for
		
		// M개의 줄에 각 조교의 지시가 주어진다.
		int[] orders = new int[N];
		int a, b, k; // 각 조교의 지시는 세 개의 정수 a, b, k로 이루어져 있다.
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()); // a번 칸부터
			b = Integer.parseInt(st.nextToken()); // b번 칸까지
			k = Integer.parseInt(st.nextToken()); // k만큼 흙 작업
			orders[a-1] += k;
			if(b<N) orders[b] -= k;
		}//while
		br.close();
		
		StringBuilder sb = new StringBuilder();
		int prev = 0;
		for(int i=0; i<N; i++) {
			prev += orders[i];
			sb.append(H[i] + prev).append(" ");
		}
		
		System.out.print(sb);
	}//main

}//class

/*

10 3
1 2 3 4 5 -1 -2 -3 -4 -5
1 5 -3
6 10 5
2 7 2
---------------
-2 1 2 3 4 6 5 2 1 0

*/