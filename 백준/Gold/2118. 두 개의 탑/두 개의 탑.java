import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2118
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		int sum = 0;
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}//for
		
		int min, max = 0;
		int a = 0, b = 0;
		int cur = arr[a];
		
		while(a <= b && b < N) {
			min = Math.min(cur, sum-cur);
			max = Math.max(max, min);
			if(cur == min) {
				b++;
				cur += arr[b];
			}else {
				cur -= arr[a];
				a++;
			}
		}//while

		System.out.print(max);
	}//main

}//class