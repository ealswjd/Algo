import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11441
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 수의 개수

		int[] sum = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			sum[i] = Integer.parseInt(st.nextToken());
			sum[i] += sum[i-1];
		}//for
		
		StringBuilder sb = new StringBuilder();
		int M  = Integer.parseInt(br.readLine());
		int a, b;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			sb.append(sum[b] - sum[a-1]).append("\n");
		}//while		
		br.close();
		
		System.out.print(sb);
	}//main

}//class