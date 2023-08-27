import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15565
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] ryan = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1, j=1, n=0; i<=N; i++) {
			n = Integer.parseInt(st.nextToken());
			if(n == 1) ryan[j++] = i;
		}//for
		br.close();
		
		if(ryan[K] == 0) System.out.print(-1);
		else {
			int min = N+1;
			int start = 1, end = K;
			int max = N - N / K;
			
			while(start <= max) {
				if(ryan[end] == 0) break;
				min = Math.min(min, ryan[end++] - ryan[start++]);
			}//while
			
			System.out.print(min+1);			
		}//else
		
	}//main

}//class