import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2467
public class Main {
	static int N;
	static int[] solution;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		solution = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			solution[i] = Integer.parseInt(st.nextToken());
		}//for
		br.close();

		mix();
	}//main

	private static void mix() {
		int start = 0;
		int end = N-1;
		int min = Math.abs(solution[start] + solution[start+1]);
		int sum = 0;
		
		int[] sol = new int[] {solution[start], solution[start+1]};
		while(start < end) {
			sum = Math.abs(solution[start] + solution[end]);
			
			if(sum == 0) {
				sol[0] = solution[start];
				sol[1] = solution[end];
				break;
			}//if
			
			if(sum < min) {
				sol[0] = solution[start];
				sol[1] = solution[end];
				min = sum;
			}//if
			
			if(Math.abs(solution[start+1] + solution[end]) <= Math.abs(solution[start] + solution[end-1])) {
				start++;
			}else end--;
		}//while
		
		StringBuilder ans = new StringBuilder();
		ans.append(sol[0] + " ").append(sol[1]);
		System.out.print(ans);
	}//mix

}//class