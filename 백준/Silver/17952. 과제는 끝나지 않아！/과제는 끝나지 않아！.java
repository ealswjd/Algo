import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17952
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Stack<int[]> stack = new Stack<>();
		StringTokenizer st;
		int A, T, score = 0;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			
			if(st.nextToken().equals("1")) {
				A = Integer.parseInt(st.nextToken());
				T = Integer.parseInt(st.nextToken());
			}else {
				if(stack.isEmpty()) continue;
				A = stack.peek()[0];
				T = stack.pop()[1];
			}//else
			
			if(--T == 0) score += A;
			else stack.add(new int[] {A, T});
		}//while
		br.close();
		
		System.out.print(score);
	}//main

}//class