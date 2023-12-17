import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2118
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int sum = 0;
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}//for
		
		int max = 0;
		int a = 0, b = 1;
		int sumA = arr[0];
		int sumB = sum - arr[0];
		while(a < N) {
			max = Math.max(max, Math.min(sumA, sumB));
			if(sumA == sumB) break;
			else if(sumA > sumB) {
				sumA -= arr[a];
				sumB += arr[a];
				a++;
				continue;
			}
			sumA += arr[b];
			sumB -= arr[b];
			b++;
			b %= N;
		}//while

		System.out.print(max);
	}//main

}//class