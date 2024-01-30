import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16943
public class Main {
	static int N, A, B, result, start;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		br.close();
		
		int bLen = (int) Math.log10(B) + 1;
		N = (int)Math.log10(A) + 1;
		visited = new boolean[N];

		if(bLen < N) System.out.print(-1);
		else {
			start = (int)Math.pow(10, N-1);
			sol(N, 0);
			if(result == 0) result = -1;
			System.out.print(result);			
		}
	}//main

	private static void sol(int cnt, int sum) {
		if(cnt == 0) {
			if((int)Math.log10(sum) + 1 == N && sum > result && sum < B) result = sum;
			return;
		}//if
		
		int n, pow = (int)Math.pow(10, cnt-1);
		for(int i=start, j=0; j<N; i/=10, j++) {
			if(visited[j]) continue;
			n = ((A / i) % 10) * pow;
			visited[j] = true;
			sol(cnt-1, sum + n);
			visited[j] = false;
		}//for
	}//sol

}//class